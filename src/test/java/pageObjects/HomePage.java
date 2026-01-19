package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[contains(text(),\"Full-Fledged practice\")]")
    private WebElement HomePageTitle;

    @FindBy(xpath = " //a[normalize-space()='Signup / Login']")
    private WebElement Signup_Login_Link;

    @FindBy(partialLinkText = "Logged in")
    private WebElement linkCheckLogin;

    @FindBy(xpath = "//a[normalize-space()='Delete Account']")
    private WebElement linkDeleteAccount;

    public boolean isHomePageVisible(){
        return isElementPresent(HomePageTitle);
    }

    public void clickSignupLogin(){
        clickElementJS(Signup_Login_Link);
    }

    public boolean isLinkLoginExist(){
        return isElementPresent(linkCheckLogin);
    }

    public void clickDeleteAccount(){
        clickElementJS(linkDeleteAccount);
    }
}
