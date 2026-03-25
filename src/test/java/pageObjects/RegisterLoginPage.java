package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterLoginPage extends BasePage{
    public RegisterLoginPage(WebDriver driver) {
        super(driver);
    }

    // 统一使用 By 定位器
    private final By SignupTitle = By.xpath("//h2[normalize-space()='New User Signup!']");
    private final By NameInput = By.xpath("//input[@placeholder='Name']");
    private final By SignupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    private final By SignupButton = By.xpath("//button[normalize-space()='Signup']");
    private final By LoginEmailInput = By.xpath("//input[@data-qa='login-email']");
    private final By passwordInput = By.xpath("//input[@placeholder='Password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By loginFailedAlert = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");
    private final By loginPageHeading = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By loginPageHeading1 = By.xpath("//h2[normalize-space()='New User Signup!']");
    private final By duplicateUserAlert = By.xpath("//p[normalize-space()='Email Address already exist!']");

    public boolean isSignupTitleVisible(){
        return isElementPresent(SignupTitle);
    }

    public void enterNameAndEmail(String name, String email){
        sendKeysToElement(NameInput, name);
        sendKeysToElement(SignupEmailInput, email);
    }

    public AccountInfoPage clickSignupButton(){
        clickElement(SignupButton);
        return new AccountInfoPage(driver);
    }

    public HomePage login(String email, String password){
        sendKeysToElement(LoginEmailInput, email);
        sendKeysToElement(passwordInput, password);
        clickElement(loginButton);
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
