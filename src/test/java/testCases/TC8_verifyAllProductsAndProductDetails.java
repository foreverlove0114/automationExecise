package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;

public class TC8_verifyAllProductsAndProductDetails extends BaseClass {

    @Test
    public void testVerifyAllProductsAndProductDetails(){
        logger.info("***** testCases.Authentication.TC8_verifyAllProductsAndProductDetails *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** HomePage Visible *****");
        //4. Click on 'Products' button
        ProductsPage pp = hp.clickNavigateToProductPage();
        //5. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(pp.checkHeadingProductPagePresent());
        //6. The products list is visible
        Assert.assertTrue(pp.isProductVisible());
        logger.info("***** Product lists is visible *****");
        //7. Click on 'View Product' of first product
        ProductDetailsPage pdp = pp.clickFirstItemToViewDetails();
        //9. Verify that detail is visible: product name, category, price, availability, condition, brand
        Assert.assertTrue(pdp.checkAllInfoVisible());
        logger.info("All product info are visible");
    }
}
