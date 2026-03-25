package pageObjects.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import pageObjects.BrandPage;

public class CategoryBrandsComponent extends BasePage {
    
    private static final Logger logger = LogManager.getLogger(CategoryBrandsComponent.class);

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
        try {
            // 等待元素可见
            waitForElementVisible(By.xpath("//a[@href='#" + mainCategory + "']"));
            
            // 点击主分类前先滚动到该位置
            By mainCat = By.xpath("//a[@href='#" + mainCategory + "']");
            scrollDownUntilText(mainCat);
            
            // 使用 JS 点击确保能点到（侧边栏可能被固定）
            clickElement(mainCat);
            
            try {
                Thread.sleep(500); // 等待子菜单展开
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                logger.warn("Thread interrupted while waiting for submenu", ie);
            }
            
            // 点击子分类
            By subCat = By.xpath("//div[@id='" + mainCategory + "']//a[contains(text(),'" + subCategory + "')]");
            waitForElementVisible(subCat);
            clickElement(subCat);
        } catch (Exception e) {
            System.err.println("Failed to select category: " + mainCategory + " -> " + subCategory);
            e.printStackTrace();
            throw e;
        }
    }

    public BrandPage selectBrand(String brand){
        // Brand 链接在侧边栏，优先使用普通点击
        By brandChosen = By.xpath("//a[@href='/brand_products/" + brand + "']");
        clickElement(brandChosen);
        return new BrandPage(driver);
    }

    public boolean isHeadingBrandExist(){
        return isElementPresent(brandHeading);
    }
}
