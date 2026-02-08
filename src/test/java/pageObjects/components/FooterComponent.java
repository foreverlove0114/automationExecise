package pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FooterComponent {
    private WebDriver driver;

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
    }

    private By inputSubscribe = By.id("susbscribe_email");
    private By btnSubscribe = By.id("subscribe");
    private By successMsg = By.xpath("//div[@class='alert-success alert']");

    public void subscribe(String email) {
        driver.findElement(inputSubscribe).sendKeys(email);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(btnSubscribe));
    }

    public boolean isSuccessMsgVisible() {
        return driver.findElement(successMsg).isDisplayed();
    }
}