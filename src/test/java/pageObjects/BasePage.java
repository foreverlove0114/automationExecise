package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.components.CategoryBrandsComponent;
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
    public CategoryBrandsComponent category;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    // ==================== 统一使用 By 定位器的方法 ====================

    protected void waitForElementVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickElement(By locator) {
        try{
            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("普通点击失败，尝试使用 JS 点击: " + locator.toString());
            clickElementJS(locator);
        }
    }

    protected void clickElementJS(By locator) {
        waitForElementVisible(locator);
        js.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    protected void clickElementJS(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].click();", element);
    }

    protected void sendKeysToElement(By locator, String text){
        waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isElementPresent(By locator){
        try{
            waitForElementVisible(locator);
            return driver.findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    protected void selectByVisibleText(By locator, String text){
        waitForElementVisible(locator);
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    // ==================== 通用页面操作方法 ====================

    public void handleAlertWithClickOK(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Content of Alert: " + alertText);
        alert.accept();
    }

    public void scrollDownUntilText(By locator){
        waitForElementVisible(locator);
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(locator));
    }

    public void addProductToCartByIndex(int index){
        By addToCartBtn = By.xpath(String.format("(//div[@class='product-overlay']//a[text()='Add to cart'])[%d]", index));
        By product = By.xpath(String.format("(//div[@class='single-products'])[%d]", index));

        scrollDownUntilText(product);
        clickElementJS(addToCartBtn);
    }

    public void clickContinueShopping(){
        clickElementJS(By.xpath("//button[normalize-space()='Continue Shopping']"));
    }

    public CartPage clickLinkViewCart(){
        clickElementJS(By.xpath("//u[normalize-space()='View Cart']"));
        return new CartPage(driver);
    }
}
