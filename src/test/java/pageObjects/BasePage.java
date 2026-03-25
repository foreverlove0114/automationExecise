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
    protected WebDriverWait shortWait;
    protected JavascriptExecutor js;
    public NavComponent nav;
    public FooterComponent footer;
    public CategoryBrandsComponent category;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.js = (JavascriptExecutor) driver;
    }

    protected void waitForElementVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * 主点击方法 - 使用 JS 触发真实点击事件
     * 结合 scroll + dispatchEvent 触发完整的鼠标事件链
     */
    protected void clickElement(By locator) {
        try {
            // 等待元素存在并可见
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            // 滚动到元素位置
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            // 短暂等待确保滚动完成且页面稳定
            Thread.sleep(200);
            
            // 使用 dispatchEvent 触发真实的 click 事件（包含冒泡）
            js.executeScript(
                "var element = arguments[0]; " +
                "element.dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));",
                element
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Click interrupted", e);
        } catch (Exception e) {
            System.out.println("点击失败：" + locator.toString() + " - " + e.getMessage());
            throw new RuntimeException("Failed to click element: " + locator, e);
        }
    }

    /**
     * Simple click for special cases
     */
    protected void clickElementSimple(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    protected void sendKeysToElement(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isElementPresent(By locator){
        try{
            WebElement element = shortWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    protected void selectByVisibleText(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void handleAlertWithClickOK(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Content of Alert: " + alertText);
        alert.accept();
    }

    public void scrollDownUntilText(By locator){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void addProductToCartByIndex(int index){
        By addToCartBtn = By.xpath(String.format("(//div[@class='productinfo text-center']//a[contains(@class,'add-to-cart')])[%d]", index));
        By altAddToCartBtn = By.xpath(String.format("(//a[@data-product-id])[%d]", index));
        By product = By.xpath(String.format("(//div[@class='productinfo text-center'])[%d]", index));
    
        scrollDownUntilText(product);
    
        try {
            clickElement(addToCartBtn);
        } catch (Exception e) {
            System.out.println("主 xpath 失败，尝试备用 xpath...");
            clickElement(altAddToCartBtn);
        }
    }
    
    public void clickContinueShopping(){
        clickElement(By.xpath("//button[normalize-space()='Continue Shopping']"));
    }
    
    public CartPage clickLinkViewCart(){
        clickElement(By.xpath("//u[normalize-space()='View Cart']"));
        return new CartPage(driver);
    }
}