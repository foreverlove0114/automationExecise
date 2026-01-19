package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends BasePage{
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//b[normalize-space()='Account Created!']")
    private WebElement accountCreatedTitle;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    private WebElement buttonContinue;

    public boolean isAccountCreatedTitleVisible(){
        return isElementPresent(accountCreatedTitle);
    }

    public void clickButtonContinue(){
        clickElementJS(buttonContinue);
    }
}
