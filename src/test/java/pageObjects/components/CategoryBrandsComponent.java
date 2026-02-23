package pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import pageObjects.BrandPage;

public class CategoryBrandsComponent extends BasePage {

    public CategoryBrandsComponent(WebDriver driver) {
        super(driver);
    }

    // Category locators
    private final By categoryTitle = By.xpath("//h2[text()='Category']");
    private final By womenCategory = By.xpath("//a[@href='#Women']");
    private final By menCategory = By.xpath("//a[@href='#Men']");
    private final By kidsCategory = By.xpath("//a[@href='#Kids']");

    // Brand locators
    private final By brandHeading = By.xpath("//h2[normalize-space()='Brands']");

    // 封装通用行为
    public void selectSubCategory(String mainCategory, String subCategory) {
        // 例如：点击 Women -> 点击 Dress
        By mainCat = By.xpath("//a[@href='#" + mainCategory + "']");
        clickElementJS(mainCat);

        By subCat = By.xpath("//div[@id='" + mainCategory + "']//a[contains(text(),'" + subCategory + "')]");
        clickElementJS(subCat);
    }

    public BrandPage selectBrand(String brand){
        By brandChosen = By.xpath("//a[@href='/brand_products/" + brand + "']");
        clickElementJS(brandChosen);
        return new BrandPage(driver);
    }

    public boolean isHeadingBrandExist(){
        return isElementPresent(brandHeading);
    }
}
