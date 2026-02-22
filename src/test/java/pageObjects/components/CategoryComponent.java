package pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;

public class CategoryComponent extends BasePage {

    public CategoryComponent(WebDriver driver) {
        super(driver);
    }

    // 定位器只需定义一次
    private final By categoryTitle = By.xpath("//h2[text()='Category']");
    private final By womenCategory = By.xpath("//a[@href='#Women']");
    private final By menCategory = By.xpath("//a[@href='#Men']");
    private final By kidsCategory = By.xpath("//a[@href='#Kids']");

    // 封装通用行为
    public void selectSubCategory(String mainCategory, String subCategory) {
        // 例如：点击 Women -> 点击 Dress
        By mainCat = By.xpath("//a[@href='#" + mainCategory + "']");
        clickElementJS(mainCat);

        By subCat = By.xpath("//div[@id='" + mainCategory + "']//a[contains(text(),'" + subCategory + "')]");
        clickElementJS(subCat);
    }
}
