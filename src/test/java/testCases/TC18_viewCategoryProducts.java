package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CategoryProductPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC18_viewCategoryProducts extends BaseClass {

    @Test
    public void testViewCategoryProducts(){
        initializeHomePage();
        //3. Verify that categories are visible on left side bar
        HomePage hp = new HomePage(getDriver());
        Assert.assertTrue(hp.isHeadingCategoryPresent());
        //4. Click on 'Women' category
        hp.clickCategoryWomen();
        //5. Click on any category link under 'Women' category, for example: Dress
        CategoryProductPage cpp = hp.clickDressLink();
        //6. Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'
        Assert.assertTrue(cpp.isHeadingWomenDressProductPresent());
    }
}
