package testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

//    protected WebDriver driver;
    // 静态的 ThreadLocal 容器
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public Logger logger;

    // 每一个线程调用这个方法来设置自己的 driver
    public void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    // 每一个线程调用这个方法来获取自己的 driver
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @Parameters({"os","browser"})
    @BeforeMethod(alwaysRun = true) //规则：在自动化框架中，所有的配置方法（@BeforeSuite, @BeforeClass, @BeforeMethod 等）都应该养成加上 (alwaysRun = true) 的习惯。
    public void setup(String os,String browser) throws IllegalAccessException {
        logger = LogManager.getLogger(this.getClass());
        WebDriver driver = null; // 此时是局部变量

        switch (browser.toLowerCase()){
            case "chrome":
//                driver = new ChromeDriver();break;
                ChromeOptions chromeOptions = new ChromeOptions();

                // 1. 处理 SSL 证书错误
                chromeOptions.setAcceptInsecureCerts(true);
                // 2. 移除 "Chrome is being controlled..." 提示
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                // 3. (可选) 设置隐身模式
                // chromeOptions.addArguments("--incognito");
                // 4. 加载广告拦截插件 (解决你之前遇到的 Google Ads 问题)
                // 请确保你已经下载了 uBlock-Origin.crx 文件并放在指定路径
                //chromeOptions.addExtensions(new File("./extensions/uBlock-Origin.crx"));
                // 5. (可选) 无头模式 - 如果要在服务器运行则开启
                // chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                // Firefox 进入隐私模式的参数
                // firefoxOptions.addArguments("-private");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setAcceptInsecureCerts(true);
                // Edge 也有类似的移除受控提示
                edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalAccessException("不支持的浏览器：" + browser);
        }

        //必须存入 ThreadLocal ---
        setDriver(driver);

        // 现在 getDriver() 才能拿到当前线程对应的那个 driver
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://automationexercise.com/");
        getDriver().manage().window().maximize();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        getDriver().quit();
        threadLocalDriver.remove(); // 彻底清理当前线程的副本
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

        // 使用 getDriver() 替代之前的 protected driver
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

//        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        // 使用正斜杠 / 适配所有系统
        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

    public void initializeHomePage(){
        logger.info("***** testCases.Authentication.TC8_verifyAllProductsAndProductDetails *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** HomePage Visible *****");
    }

    public void fillInRegister(){
        RegisterLoginPage rlp = new RegisterLoginPage(getDriver());
        HomePage hp = new HomePage(getDriver());
        rlp.enterNameAndEmail(randonString(),randomAlphaNumeric() + "gmail.com");
        AccountInfoPage aip = rlp.clickSignupButton();
        aip.fillInAccountInfo(TestData.PASSWORD,TestData.DAY,TestData.MONTH,TestData.YEAR);
        //Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
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
        //Click 'Create Account button'
        AccountCreatedPage acp = aip.clickCreateAccountButton();
        //6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertTrue(acp.isAccountCreatedTitleVisible());
        hp = acp.clickButtonContinue();
        //7. Verify ' Logged in as username' at top
        Assert.assertTrue(hp.isLinkLoginExist());
    }

    public void proceedToCheckOut(){
        HomePage hp = new HomePage(getDriver());
        hp.addProductToCartByIndex(1);
        hp.clickContinueShopping();
        hp.addProductToCartByIndex(2);
        //9. Click 'Cart' button
        CartPage cp = hp.clickLinkViewCart();
        //10. Verify that cart page is displayed
        Assert.assertTrue(cp.isCheckoutButtonExist());
    }

    public void proceedPayment(){
        OrderPage op = new OrderPage(getDriver());
        Assert.assertTrue(op.isAddressAndOrderInfoPresent());
        //15. Enter description in comment text area and click 'Place Order'
        op.inputTextIntoComment("Please take care of the products");
        PaymentPage pp = op.clickPlaceOrder();
        //16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        //17. Click 'Pay and Confirm Order' button
        PaymentSuccessPage psp = pp.makePayment(TestData.nameOnCard,TestData.cardNum, TestData.cvc,TestData.ExpirationMM,TestData.ExpirationYYYY);
        //18. Verify success message 'Your order has been placed successfully!'
        Assert.assertTrue(psp.isSuccessAlertExist());
    }
}
