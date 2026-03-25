package pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;

public class FooterComponent extends BasePage {

    public FooterComponent(WebDriver driver) {
        super(driver); // 关键：把驱动交给父类处理
    }

    private By inputSubscribe = By.id("susbscribe_email");
    private By btnSubscribe = By.id("subscribe");
    private By successMsg = By.xpath("//div[@class='alert-success alert']");

    public void subscribe(String email) {
        driver.findElement(inputSubscribe).sendKeys(email);
        // Footer 中的订阅按钮可能被固定在页面底部，使用 JS 点击更可靠
        clickElement(btnSubscribe);
    }

    public boolean isSuccessMsgVisible() {
        return driver.findElement(successMsg).isDisplayed();
    }
}