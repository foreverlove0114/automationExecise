package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.components.CategoryBrandsComponent;
import pageObjects.components.NavComponent;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage{
    public ProductsPage(WebDriver driver) {
        super(driver);
        this.nav = new NavComponent(driver);
        this.category = new CategoryBrandsComponent(driver);
    }

    // 统一使用 By 定位器，支持动态 xpath
    private final By headingProductPage = By.xpath("//h2[normalize-space()='All Products']");
    private final By allProducts = By.xpath("//div[@class='features_items']//div[@class='col-sm-4']");
    private final By allViewProduct = By.xpath("//i[contains(@class,'fa-plus-square')]/parent::a");
    private final By searchInput = By.xpath("//input[@id='search_product']");
    private final By searchButton = By.xpath("//button[@id='submit_search']");
    private final By searchProducts = By.xpath("//h2[normalize-space()='Searched Products']");
    private final By productName = By.xpath("//div[@class='productinfo text-center']//p[contains(text(),'Blue Top')]");
    private final By buttonContinueShopping = By.xpath("//button[normalize-space()='Continue Shopping']");
    private final By cartLink = By.xpath("//a[text()=' Cart']");

    // 动态 xpath 模板方法
    private By getProductWrapperBy(int index) {
        return By.xpath(String.format("//div[@class='features_items']//div[@class='col-sm-4'][%d]", index));
    }

    private By getAddToCartBtnBy(int index) {
        return By.xpath(String.format("(//div[@class='product-overlay']//a[text()='Add to cart'])[%d]", index));
    }


    public boolean checkHeadingProductPagePresent(){
        return isElementPresent(headingProductPage);
    }

    public boolean isProductVisible(){
        if(!isElementPresent(headingProductPage)){
            return false;
        }

        List<WebElement> products = driver.findElements(allProducts);
        return (!products.isEmpty() && products.get(0).isDisplayed());
    }

    public ProductDetailsPage clickFirstItemToViewDetails(){
        List<WebElement> viewProducts = driver.findElements(allViewProduct);
        // 检查列表是否为空
        if (!viewProducts.isEmpty()) {
            // 点击第一个
            clickElementJS(viewProducts.get(0));
        } else {
            throw new RuntimeException("未能找到任何 'View Product' 链接");
        }
        // 返回详情页对象
        return new ProductDetailsPage(driver);
    }

    public void searchItem(String text){
        sendKeysToElement(searchInput, text);
        clickElementJS(searchButton);
    }

    public boolean isSearchProductHeadingPresent(){
        return isElementPresent(searchProducts);
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }

    public boolean isButtonContinueShoppingExist(){
        try {
            WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonContinueShopping));
            return btn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public CartPage navigateToCart(){
        clickElementJS(cartLink);
        return new CartPage(driver);
    }
}
