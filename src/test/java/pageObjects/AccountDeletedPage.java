package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDeletedPage extends BasePage{
    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//b[normalize-space()='Account Deleted!']")
    private WebElement deleteAccountTitle;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    private WebElement buttonContinue;

    public boolean isAccountDeletedTitleVisible(){
        return isElementPresent(deleteAccountTitle);
    }

    public void clickButtonContinue(){
        clickElementJS(buttonContinue);
    }
}
