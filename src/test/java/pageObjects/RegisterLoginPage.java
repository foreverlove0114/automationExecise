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

    public boolean isSignupTitleVisible(){
        return isElementPresent(SignupTitle);
    }

    public void enterNameAndEmail(String name,String email){
        sendKeysToElement(NameInput,name);
        sendKeysToElement(SignupEmailInput,email);
    }

    public void clickSignupButton(){
        clickElementJS(SignupButton);
    }
}
