package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage{
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    // 统一使用 By 定位器
    private final By textGetInTouch = By.xpath("//h2[normalize-space()='Get In Touch']");
    private final By nameInput = By.xpath("//input[@placeholder='Name']");
    private final By emailInput = By.xpath("//input[@placeholder='Email']");
    private final By subjectInput = By.xpath("//input[@placeholder='Subject']");
    private final By messageInput = By.xpath("//textarea[@id='message']");
    private final By fileUpload = By.xpath("//input[@name='upload_file']");
    private final By buttonSubmit = By.xpath("//input[@name='submit']");
    private final By alertFormSubmitted = By.xpath("//div[@class='status alert alert-success']");
    private final By buttonBackToHome = By.xpath("//span[normalize-space()='Home']");

    public boolean checkWhetherGetInTouchPresent(){
        return isElementPresent(textGetInTouch);
    }

    public void fillInfoAndMsg(String name, String email, String subject, String message){
        sendKeysToElement(nameInput, name);
        sendKeysToElement(emailInput, email);
        sendKeysToElement(subjectInput, subject);
        sendKeysToElement(messageInput, message);
    }

    public void uploadFileAndSubmit(String file){
        sendKeysToElement(fileUpload, file);
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
