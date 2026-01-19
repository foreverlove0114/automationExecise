package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    protected void waitForElementVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

//    protected void clickElement(WebElement element){
//        waitForElementClickable(element);
//        element.click();
//    }

    protected void clickElementJS(WebElement element) {
        waitForElementVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected void sendKeysToElement(WebElement element, String text){
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isElementPresent(WebElement element){
        try{
            waitForElementVisible(element);
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    protected void selectByVisibleText(WebElement element,String text){
        waitForElementVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
}
