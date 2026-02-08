package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage{
    private final By addressDetailsHeading = By.xpath("//h2[normalize-space()='Address Details']");
    private final By reviewOrderHeading = By.xpath("//h2[normalize-space()='Review Your Order']");
    private final By commentField = By.name("message");
    private final By btnPlaceOrder = By.linkText("Place Order");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAddressAndOrderInfoPresent(){
        return isElementPresent(addressDetailsHeading) && isElementPresent(reviewOrderHeading);
    }

    public void inputTextIntoComment(String text){
        sendKeysToElement(commentField,text);
    }

    public PaymentPage clickPlaceOrder(){
        clickElement(btnPlaceOrder);
        return new PaymentPage(driver);
    }
}
