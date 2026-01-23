package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage{
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='Get In Touch']")
    private WebElement textGetInTouch;

    @FindBy(xpath = "//input[@placeholder='Name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='Subject']")
    private WebElement subjectInput;

    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement messageInput;

    @FindBy(xpath = "//input[@name='upload_file']")
    private WebElement fileUpload;

    @FindBy(xpath = "//input[@name='submit']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    private WebElement alertFormSubmitted;

    @FindBy(xpath = "//span[normalize-space()='Home']")
    private WebElement buttonBackToHome;

    public boolean checkWhetherGetInTouchPresent(){
        return isElementPresent(textGetInTouch);
    }

    public void fillInfoAndMsg(String name,String email,String subject,String message){
        sendKeysToElement(nameInput,name);
        sendKeysToElement(emailInput,email);
        sendKeysToElement(subjectInput,subject);
        sendKeysToElement(messageInput,message);
    }

    public void uploadFileAndSubmit(String file){
        sendKeysToElement(fileUpload,file);
        clickElementJS(buttonSubmit);
    }

    public boolean isFormSubmittedSuccessfully(){
        return isElementPresent(alertFormSubmitted);
    }

    public HomePage navigateToHomePageAndVerify(){
        clickElementJS(buttonBackToHome);
        return new HomePage(driver);
    }
}
