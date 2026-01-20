package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterLoginPage extends BasePage{
    public RegisterLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='New User Signup!']")
    private WebElement SignupTitle;

    @FindBy(xpath = "//input[@placeholder='Name']")
    private WebElement NameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement SignupEmailInput;

    @FindBy(xpath = "//button[normalize-space()='Signup']")
    private WebElement SignupButton;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement LoginEmailInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    private WebElement loginFailedAlert;

    @FindBy(xpath = "//h2[normalize-space()='Login to your account']")
    private WebElement loginPageHeading;

    @FindBy(xpath = "//h2[normalize-space()='New User Signup!']")
    private WebElement loginPageHeading1;

    @FindBy(xpath = "//h2[normalize-space()='New User Signup!']")
    private WebElement duplicateUserAlert;

    public boolean isSignupTitleVisible(){
        return isElementPresent(SignupTitle);
    }

    public void enterNameAndEmail(String name,String email){
        sendKeysToElement(NameInput,name);
        sendKeysToElement(SignupEmailInput,email);
    }

    public AccountInfoPage clickSignupButton(){
        clickElementJS(SignupButton);
        return new AccountInfoPage(driver);
    }

    public HomePage login(String email,String password){
        sendKeysToElement(LoginEmailInput,email);
        sendKeysToElement(passwordInput,password);
        clickElementJS(loginButton);
        return new HomePage(driver);
    }

    public boolean isAlertPresent(){
        return isElementPresent(loginFailedAlert);
    }

    public boolean checkNavigatedLoginPage(){
        return isElementPresent(loginPageHeading) && isElementPresent(loginPageHeading1);
    }

    public boolean isDuplicateUserAlertPresent(){
        return isElementPresent(duplicateUserAlert);
    }
}
