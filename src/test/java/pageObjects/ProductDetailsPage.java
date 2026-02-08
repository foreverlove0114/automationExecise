package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[contains(text(),'Category')]")
    private WebElement categoryText;

    @FindBy(xpath = "//label[normalize-space()='Quantity:']")
    private WebElement quantityText;

    @FindBy(xpath = "//b[normalize-space()='Availability:']")
    private WebElement availabilityText;

    @FindBy(xpath = "//b[normalize-space()='Condition:']")
    private WebElement conditionText;

    @FindBy(xpath = "//b[normalize-space()='Brand:']")
    private WebElement brandTitle;

    @FindBy(xpath = "//span[starts-with(text(),'Rs.')]")
    private WebElement priceText;

    @FindBy(xpath = "//input[@id='quantity']")
    private WebElement quantityField;

    @FindBy(xpath = "//button[normalize-space()='Add to cart']")
    private WebElement btnAddToCart;

    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    private WebElement linkViewCart;

    public boolean checkAllInfoVisible(){
        return isElementPresent(categoryText) &&
                isElementPresent(quantityText) &&
                isElementPresent(availabilityText) &&
                isElementPresent(conditionText) &&
                isElementPresent(brandTitle) &&
                isElementPresent(priceText);
    }

    public void setQuantity(String quantity){
        sendKeysToElement(quantityField,quantity);
    }

    public void clickAddToCart(){
        clickElementJS(btnAddToCart);
    }

    public CartPage clickLinkViewCart(){
        clickElementJS(linkViewCart);
        return new CartPage(driver);
    }
}
