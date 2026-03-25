package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC22_addToCartFromRecommended extends BaseClass {

    @Test
    public void testAddToCartFromRecommended(){
        logger.info("***** Starting TC22_addToCartFromRecommended *****");
        HomePage hp = new HomePage(getDriver());
        
        //1. Launch browser - handled by @BeforeMethod
        //2. Navigate to url 'http://automationexercise.com' - handled by @BeforeMethod
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** Home page is visible *****");
        
        //3. Scroll to bottom of page
        hp.scrollToRecommendItems();
        logger.info("***** Scrolled to recommended items section *****");
        
        //4. Verify 'RECOMMENDED ITEMS' are visible
        Assert.assertTrue(hp.isHeadingRecommendedItemsExist(), "Recommended items heading not found");
        logger.info("***** Recommended items section is visible *****");
        
        //5. Click on 'Add To Cart' on Recommended product (first one)
        hp.clickFirstRecommendedProductAddToCart();
        logger.info("***** Clicked Add to Cart on first recommended product *****");
        
        //6. Click on 'View Cart' button
        CartPage cp = hp.clickLinkViewCart();
        logger.info("***** Navigated to Cart page *****");
        
        //7. Verify that product is displayed in cart page
        Assert.assertTrue(cp.countItemsInCart() >= 1, "Cart should have at least 1 item");
        logger.info("✓ Product successfully added to cart from recommended items");
    }
}
