package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.components.FooterComponent;
import pageObjects.components.NavComponent;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    // 实例化组件
    public NavComponent nav;
    public FooterComponent footer;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.nav = new NavComponent(driver);
        this.footer = new FooterComponent(driver);
        PageFactory.initElements(driver,this);
    }

    protected void waitForElementVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //overloading method - By
    protected void waitForElementVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // overloading method - By
    protected void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    //overloading method - By
    protected void clickElement(By locator) {
        waitForElementClickable(locator);

        try{
            driver.findElement(locator).click();
        } catch (Exception e) {
            System.out.println("普通点击失败，尝试使用 JS 点击: " + locator.toString());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
        }
    }

    protected void clickElementJS(WebElement element) {
        waitForElementVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected void sendKeysToElement(WebElement element, String text){
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    //overloading method - By
    protected void sendKeysToElement(By locator, String text){
        waitForElementVisible(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected boolean isElementPresent(WebElement element){
        try{
            waitForElementVisible(element);
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    //overloading method - By
    protected boolean isElementPresent(By locator){
        try{
            waitForElementVisible(locator);
            return driver.findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    protected void selectByVisibleText(WebElement element,String text){
        waitForElementVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void handleAlertWithClickOK(){
        /*
        alert.accept()	点击“确定”或“OK”	处理你的 OK 按钮
        alert.dismiss()	点击“取消”或“关闭”	处理你的 Cancel 按钮
        alert.getText()	获取弹窗上的文字内容	验证是否出现了正确的提示
        alert.sendKeys("text")	在弹窗输入框中输入文字	仅适用于 prompt() 类型的弹窗
        * */

        // 1. 使用显式等待，确保弹窗已经跳出来
        wait.until(ExpectedConditions.alertIsPresent());

        // 2. 切换到弹窗
        Alert alert = driver.switchTo().alert();

        // 3. 读取弹窗文本 (例如: "Press OK to proceed!")
        String alertText = alert.getText();
        System.out.println("Content of Alert: " + alertText);

        // 4. 点击 OK (Accept) 或者 Cancel (Dismiss)
        alert.accept();// 相当于点击 OK
    }

    public void scrollDownUntilText(WebElement element){
        waitForElementVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()",element);
    }

    public void addProductToCartByIndex(int index){
        // 1. 动态生成定位器 (直接找按钮)
        String btnXpath = String.format("(//div[@class='product-overlay']//a[text()='Add to cart'])[%d]", index);
        WebElement addToCartBtn = driver.findElement(By.xpath(btnXpath));

        // 2. 滚动到商品位置（好习惯，确保元素加载）
        String productXpath = String.format("(//div[@class='single-products'])[%d]", index);
        WebElement product = driver.findElement(By.xpath(productXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);

        // 3. 直接执行 JS 点击
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", addToCartBtn);
    }

    public void clickContinueShopping(){
        clickElementJS(driver.findElement(By.xpath("//button[normalize-space()='Continue Shopping']")));
    }

    public CartPage clickLinkViewCart(){
        clickElementJS(driver.findElement(By.xpath("//u[normalize-space()='View Cart']")));
        return new CartPage(driver);
    }

}
