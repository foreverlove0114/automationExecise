package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    // 统一使用 By 定位器
    private final By categoryText = By.xpath("//p[contains(text(),'Category')]");
    private final By quantityText = By.xpath("//label[normalize-space()='Quantity:']");
    private final By availabilityText = By.xpath("//b[normalize-space()='Availability:']");
    private final By conditionText = By.xpath("//b[normalize-space()='Condition:']");
    private final By brandTitle = By.xpath("//b[normalize-space()='Brand:']");
    private final By priceText = By.xpath("//span[starts-with(text(),'Rs.')]");
    private final By quantityField = By.xpath("//input[@id='quantity']");
    private final By btnAddToCart = By.xpath("//button[normalize-space()='Add to cart']");
    private final By linkViewCart = By.xpath("//u[normalize-space()='View Cart']");
    private final By linkWriteReview = By.xpath("//a[normalize-space()='Write Your Review']");
    private final By inputName = By.xpath("//input[@id='name']");
    private final By inputEmail = By.xpath("//input[@id='email']");
    private final By inputReview = By.xpath("//textarea[@id='review']");
    private final By btnSubmitReview = By.xpath("//button[@id='button-review']");
    private final By alertReviewSuccess = By.xpath("//div[@class='alert-success alert']//span");

    public boolean checkAllInfoVisible(){
        return isElementPresent(categoryText) &&
                isElementPresent(quantityText) &&
                isElementPresent(availabilityText) &&
                isElementPresent(conditionText) &&
                isElementPresent(brandTitle) &&
                isElementPresent(priceText);
    }

    public void setQuantity(String quantity){
        sendKeysToElement(quantityField, quantity);
    }

    public void clickAddToCart(){
        clickElement(btnAddToCart);
    }

    public CartPage clickLinkViewCart(){
        clickElement(linkViewCart);
        return new CartPage(driver);
    }

    public boolean isWriteReviewVisible(){
        // 先滚动到评论区域
        try {
            WebElement reviewLink = wait.until(ExpectedConditions.presenceOfElementLocated(linkWriteReview));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", reviewLink);
            Thread.sleep(300);
            return reviewLink.isDisplayed();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterReviewDetails(String name, String email, String review){
        // 确保表单区域可见
        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputName));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", nameField);
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sendKeysToElement(inputName, name);
        sendKeysToElement(inputEmail, email);
        sendKeysToElement(inputReview, review);
    }

    public void clickSubmitReview(){
        // 使用 JS 点击提交按钮，避免可能的遮挡问题
        try {
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(btnSubmitReview));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);
            Thread.sleep(200);
            js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));", submitBtn);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to click submit review button", e);
        }
    }

    public boolean isReviewSuccessMessageVisible(){
        try {
            // 等待成功消息出现，可能需要一点时间
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(alertReviewSuccess));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", successMsg);
            Thread.sleep(200);
            return successMsg.isDisplayed();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
