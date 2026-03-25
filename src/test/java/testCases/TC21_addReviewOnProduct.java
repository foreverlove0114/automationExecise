package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import pageObjects.ProductsPage;
import testBase.BaseClass;

public class TC21_addReviewOnProduct extends BaseClass {

    @Test
    public void testAddReviewOnProduct(){
        logger.info("***** Starting TC21_addReviewOnProduct *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** HomePage Visible *****");
        //4. Click on 'Products' button
        ProductsPage pp = hp.clickNavigateToProductPage();
        //5. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(pp.checkHeadingProductPagePresent());
        logger.info("***** Navigated to All Products Page *****");
        //6. Click on 'View Product' button
        ProductDetailsPage pdp = pp.clickFirstItemToViewDetails();
        //7. Verify 'Write Your Review' is visible
        Assert.assertTrue(pdp.isWriteReviewVisible());
        logger.info("***** Write Your Review section is visible *****");
        //8. Enter name, email and review
        pdp.enterReviewDetails(randonString(), randomAlphaNumeric() + "gmail.com", "This is a test review for the product.");
        //9. Click 'Submit' button
        pdp.clickSubmitReview();
        //10. Verify success message 'Thank you for your review.'
        Assert.assertTrue(pdp.isReviewSuccessMessageVisible());
        logger.info("***** Review submitted successfully *****");
    }
}
