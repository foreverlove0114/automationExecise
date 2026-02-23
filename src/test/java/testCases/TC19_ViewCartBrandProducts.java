package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BrandPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import testBase.BaseClass;

public class TC19_ViewCartBrandProducts extends BaseClass {

    @Test
    public void testViewCartBrandProducts(){
        HomePage hp = new HomePage(getDriver());
        //3. Click on 'Products' button
        ProductsPage pp = hp.nav.clickProducts();
        //4. Verify that Brands are visible on left side bar
        Assert.assertTrue(pp.category.isHeadingBrandExist());
        //5. Click on any brand name
        BrandPage bp = pp.category.selectBrand("Polo");
        //6. Verify that user is navigated to brand page and brand products are displayed
        Assert.assertTrue(bp.isNavigatedToBrandPage("Polo"));
        //7. On left side bar, click on any other brand link
        bp.category.selectBrand("Madame");
        //8. Verify that user is navigated to that brand page and can see products
        Assert.assertTrue(bp.isNavigatedToBrandPage("Madame"));
    }
}
