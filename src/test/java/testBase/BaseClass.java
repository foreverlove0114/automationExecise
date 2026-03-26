package testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Parameters;
import pageObjects.*;
import utilities.TestData;


public class BaseClass {

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public Logger logger;

    public void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @Parameters({"os","browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(String os, String browser) throws IllegalAccessException {
        logger = LogManager.getLogger(this.getClass());
        WebDriver driver = null;

        switch (browser.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setAcceptInsecureCerts(true);
                edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalAccessException("不支持的浏览器：" + browser);
        }

        setDriver(driver);
        // 设置隐式等待为 0，完全依赖显式等待
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        getDriver().get("https://automationexercise.com/");
        getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        getDriver().quit();
        threadLocalDriver.remove();
    }

    public String randonString(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber(){
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric(){
        String str = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);
        return (str + "@" + num);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
    
    public void initializeHomePage(){
        logger.info("***** testCases.Authentication.TC8_verifyAllProductsAndProductDetails *****");
        HomePage hp = new HomePage(getDriver());
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** HomePage Visible *****");
    }

    public void fillInRegister(){
        RegisterLoginPage rlp = new RegisterLoginPage(getDriver());
        HomePage hp = new HomePage(getDriver());
        rlp.enterNameAndEmail(randonString(), randomAlphaNumeric() + "gmail.com");
        AccountInfoPage aip = rlp.clickSignupButton();
        aip.fillInAccountInfo(TestData.PASSWORD, TestData.DAY, TestData.MONTH, TestData.YEAR);
        aip.fillInAddressInfo(TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.COMPANY,
                TestData.ADDRESS1,
                TestData.ADDRESS2,
                TestData.COUNTRY,
                TestData.STATE,
                TestData.CITY,
                TestData.ZIPCODE,
                randomNumber());
        AccountCreatedPage acp = aip.clickCreateAccountButton();
        Assert.assertTrue(acp.isAccountCreatedTitleVisible());
        hp = acp.clickButtonContinue();
        Assert.assertTrue(hp.isLinkLoginExist());
    }

    public void proceedToCheckOut(){
        HomePage hp = new HomePage(getDriver());
        hp.addProductToCartByIndex(1);
        hp.clickContinueShopping();
        hp.addProductToCartByIndex(2);
        CartPage cp = hp.clickLinkViewCart();
        Assert.assertTrue(cp.isCheckoutButtonExist());
    }

    public void proceedPayment(){
        OrderPage op = new OrderPage(getDriver());
        Assert.assertTrue(op.isAddressAndOrderInfoPresent());
        op.inputTextIntoComment("Please take care of the products");
        PaymentPage pp = op.clickPlaceOrder();
        PaymentSuccessPage psp = pp.makePayment(TestData.nameOnCard, TestData.cardNum, TestData.cvc, TestData.ExpirationMM, TestData.ExpirationYYYY);
        Assert.assertTrue(psp.isSuccessAlertExist());
    }
}
