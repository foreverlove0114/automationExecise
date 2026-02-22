package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.components.CategoryComponent;

public class CategoryProductPage extends BasePage{
    public CategoryProductPage(WebDriver driver) {
        super(driver);
        this.category = new CategoryComponent(driver);
    }

    private final By headingWomenDressProduct = By.xpath("//h2[normalize-space()='Women - Dress Products']");
    private final By headingMenTshirtsProduct = By.xpath("//h2[normalize-space()='Men - Tshirts Products']");

    public boolean isHeadingWomenDressProductPresent(){
        return isElementPresent(headingWomenDressProduct);
    }

    public boolean isHeadingMenTshirtsProductPresent(){
        return isElementPresent(headingMenTshirtsProduct);
    }
}
