package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage{
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='All Products']")
    private WebElement headingProductPage;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
    private List<WebElement> allProducts;

    @FindBy(xpath = "//i[contains(@class,'fa-plus-square')]/parent::a")
    private List<WebElement> allViewProduct;

    @FindBy(xpath = "//input[@id='search_product']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@id='submit_search']")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[normalize-space()='Searched Products']")
    private WebElement searchProducts;

    @FindBy(xpath = "//div[@class='productinfo text-center']//p[contains(text(),'Blue Top')]")
    private WebElement productName;

    public boolean checkHeadingProductPagePresent(){
        return isElementPresent(headingProductPage);
    }

    public boolean isProductVisible(){
        if(!isElementPresent(headingProductPage)){
            return false;
        }

        return (!allProducts.isEmpty() && allProducts.get(0).isDisplayed());
    }

    public ProductDetailsPage clickFirstItemToViewDetails(){
        // 检查列表是否为空
        if (!allViewProduct.isEmpty()) {
            // 点击第一个
            clickElementJS(allViewProduct.get(0));
        } else {
            throw new RuntimeException("未能找到任何 'View Product' 链接");
        }
        // 返回详情页对象
        return new ProductDetailsPage(driver);
    }

    public void searchItem(String text){
        sendKeysToElement(searchInput,text);
        clickElementJS(searchButton);
    }

    public boolean isSearchProductHeadingPresent(){
        return isElementPresent(searchProducts);
    }

    public String getProductName(){
        return productName.getText();
    }
}
