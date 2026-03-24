package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.components.NavComponent;

import java.util.List;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
        this.nav = new NavComponent(driver);
    }

    // 统一使用 By 定位器
    private final By textSubscription = By.xpath("//h2[normalize-space()='Subscription']");
    private final By emailSubscriptionInput = By.xpath("//input[@id='susbscribe_email']");
    private final By clickSubscription = By.xpath("//button[@id='subscribe']");
    private final By alertSubscribeSuccess = By.xpath("//div[@class='alert-success alert']");
    private final By productInCart = By.xpath("//table[@id='cart_info_table']//tr[not(parent::thead)]");
    private final By btnCheckout = By.xpath("//a[normalize-space()='Proceed To Checkout']");
    private final By linkJumpRegisterLogin = By.xpath("//u[normalize-space()='Register / Login']");
    private final By cartIsEmptyAlert = By.xpath("//b[normalize-space()='Cart is empty!']");

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
        sendKeysToElement(emailSubscriptionInput, text);
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

    public boolean isCartEmptyAlertPresent(){
        return isElementPresent(cartIsEmptyAlert);
    }

    public void removeProductByID(int index){
        String removeBtnXpath = String.format("//a[@data-product-id=%d]",index);
//        driver.findElement(By.xpath(removeBtnXpath)).click();
        clickElement(By.xpath(removeBtnXpath));
    }
}
