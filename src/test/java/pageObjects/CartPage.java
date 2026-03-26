package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        clickElement(clickSubscription);
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
        clickElement(btnCheckout);
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
        // 直接使用 data-product-id 属性定位删除按钮（更可靠）
        By deleteBtnLocator = By.xpath(String.format("//a[@data-product-id='%d']", index));
        try {
            // 等待元素存在
            WebElement deleteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(deleteBtnLocator));
            // 滚动到元素位置
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteBtn);
            Thread.sleep(300);
            // 使用 dispatchEvent 触发点击
            js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));", deleteBtn);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("删除产品失败 - 中断异常", e);
        } catch (Exception e) {
            System.out.println("删除产品失败：" + deleteBtnLocator + " - " + e.getMessage());
            throw new RuntimeException("无法删除产品，元素未找到：" + deleteBtnLocator, e);
        }
    }
}
