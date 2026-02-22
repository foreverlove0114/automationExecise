package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryProductPage extends BasePage{
    public CategoryProductPage(WebDriver driver) {
        super(driver);
    }

    private final By headingWomenDressProduct = By.xpath("//h2[normalize-space()='Women - Dress Products']");

    public boolean isHeadingWomenDressProductPresent(){
        return isElementPresent(headingWomenDressProduct);
    }
}
