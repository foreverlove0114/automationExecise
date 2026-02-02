package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    private WebElement textSubscription;

    @FindBy(xpath = "//input[@id='susbscribe_email']")
    private WebElement emailSubscriptionInput;

    @FindBy(xpath = "//button[@id='subscribe']")
    private WebElement clickSubscription;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    private WebElement alertSubscribeSuccess;

    public boolean isTextSubscriptionPresent(){
        return isElementPresent(textSubscription);
    }

    public void scrollToSubscription(){
        scrollDownUntilText(textSubscription);
    }

    public void subscribe(String text){
        sendKeysToElement(emailSubscriptionInput,text);
        clickElementJS(clickSubscription);
    }

    public boolean checkSubscription(){
        return isElementPresent(alertSubscribeSuccess);
    }
}
