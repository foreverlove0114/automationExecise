package testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    @BeforeMethod
    public void setup(String os,String browser){
        logger = LogManager.getLogger(this.getClass());
        WebDriver driver = null; // 此时是局部变量

        switch (browser.toLowerCase()){
            case "chrome":driver = new ChromeDriver();break;
            case "firefox":driver = new FirefoxDriver();break;
            case "edge":driver = new EdgeDriver();break;
        }

        //必须存入 ThreadLocal ---
        setDriver(driver);

        // 现在 getDriver() 才能拿到当前线程对应的那个 driver
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://automationexercise.com/");
        getDriver().manage().window().maximize();

    }

    @AfterMethod
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

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
