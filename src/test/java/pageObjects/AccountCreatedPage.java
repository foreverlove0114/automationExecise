package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage{
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    // 统一使用 By 定位器
    private final By accountCreatedTitle = By.xpath("//b[normalize-space()='Account Created!']");
    private final By buttonContinue = By.xpath("//a[normalize-space()='Continue']");

    public boolean isAccountCreatedTitleVisible(){
        return isElementPresent(accountCreatedTitle);
    }

    public HomePage clickButtonContinue(){
        clickElement(buttonContinue);
        return new HomePage(driver);
    }
}
