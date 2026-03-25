package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage{
    private final By addressDetailsHeading = By.xpath("//h2[normalize-space()='Address Details']");
    private final By reviewOrderHeading = By.xpath("//h2[normalize-space()='Review Your Order']");
    private final By commentField = By.name("message");
    private final By btnPlaceOrder = By.linkText("Place Order");
    private final By linkRegisterLogin = By.xpath("//u[normalize-space()='Register / Login']");

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
        // Place Order 按钮可能被其他元素遮挡，但优先尝试普通点击
        clickElement(btnPlaceOrder);
        return new PaymentPage(driver);
    }

    public RegisterLoginPage jumpToRegisterLogin(){
        clickElement(linkRegisterLogin);
        return new RegisterLoginPage(driver);
    }
}
