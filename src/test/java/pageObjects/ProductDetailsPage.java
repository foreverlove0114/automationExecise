package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        clickElementJS(btnAddToCart);
    }

    public CartPage clickLinkViewCart(){
        clickElementJS(linkViewCart);
        return new CartPage(driver);
    }

    public boolean isWriteReviewVisible(){
        return isElementPresent(linkWriteReview);
    }

    public void enterReviewDetails(String name, String email, String review){
        sendKeysToElement(inputName, name);
        sendKeysToElement(inputEmail, email);
        sendKeysToElement(inputReview, review);
    }

    public void clickSubmitReview(){
        clickElementJS(btnSubmitReview);
    }

    public boolean isReviewSuccessMessageVisible(){
        return isElementPresent(alertReviewSuccess);
    }
}
