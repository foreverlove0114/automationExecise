package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage{
    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    // 统一使用 By 定位器
    private final By deleteAccountTitle = By.xpath("//b[normalize-space()='Account Deleted!']");
    private final By buttonContinue = By.xpath("//a[normalize-space()='Continue']");

    public boolean isAccountDeletedTitleVisible(){
        return isElementPresent(deleteAccountTitle);
    }

    public void clickButtonContinue(){
        clickElement(buttonContinue);
    }
}
