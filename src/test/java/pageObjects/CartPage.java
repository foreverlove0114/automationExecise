package pageObjects;

import org.apache.logging.log4j.core.config.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(xpath = "//table[@id='cart_info_table']//tr[not(parent::thead)]")
    private List<WebElement> productInCart;

    @FindBy(xpath = "//a[normalize-space()='Proceed To Checkout']")
    private WebElement btnCheckout;

    @FindBy(xpath = "//u[normalize-space()='Register / Login']")
    private WebElement linkJumpRegisterLogin;

    // 动态定位器模板 (By 对象写法)
    private By getPriceXpath(int index) {
        return By.xpath("(//td[@class='cart_price']/p)[" + index + "]");
    }
    private By getQuantityXpath(int index) {
        return By.xpath("(//td[@class='cart_quantity']/button)[" + index + "]");
    }
    private By getTotalPriceXpath(int index) {
        return By.xpath("(//td[@class='cart_total']/p)[" + index + "]");
    }

    public String getItemPrice(int index) {
        return driver.findElement(getPriceXpath(index)).getText();
    }

    public String getItemQuantity(int index) {
        return driver.findElement(getQuantityXpath(index)).getText();
    }

    public String getItemTotalPrice(int index) {
        return driver.findElement(getTotalPriceXpath(index)).getText();
    }

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

    public int countItemsInCart() {
        return driver.findElements(By.xpath("//table[@id='cart_info_table']/tbody/tr")).size();
    }

    public boolean isCheckoutButtonExist(){
        return isElementPresent(btnCheckout);
    }

    public OrderPage proceedToCheckout(){
        clickElementJS(btnCheckout);
        return new OrderPage(driver);
    }

    public RegisterLoginPage jumpRegisterLogin(){
        clickElement(linkJumpRegisterLogin);
        return new RegisterLoginPage(driver);
    }
}
