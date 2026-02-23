package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.components.CategoryBrandsComponent;

public class BrandPage extends BasePage{
    public BrandPage(WebDriver driver) {
        super(driver);
        this.category = new CategoryBrandsComponent(driver);
    }

    //后续优化：headingBrand 不应写死为 "Polo"，否则点击其他品牌时会验证失败。
//    private By headingBrand = By.xpath("//h2[normalize-space()='Brand - Polo Products']");
    private By productCards = By.xpath("//div[@class='features_items']//div[@class='col-sm-4']");

    private boolean isProductsListNotNull(){
        int productSize = driver.findElements(productCards).size();
        return productSize>0;
    }

    public boolean isNavigatedToBrandPage(String brand){
        By headingBrand = By.xpath("//h2[normalize-space()='Brand - " + brand + " Products']");
        return isElementPresent(headingBrand) && isProductsListNotNull();
    }
}
