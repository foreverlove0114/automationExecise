package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductDetailsPage;
import testBase.BaseClass;

public class TC13_verifyProductQuantity extends BaseClass {

    @Test
    public void testVerifyProductQuantity(){
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Click 'View Product' for any product on home page
        ProductDetailsPage pdp = hp.clickRandomViewProduct();
        //5. Verify product detail is opened
        Assert.assertTrue(getDriver().getCurrentUrl().contains("product_details"));
        // 6. Increase quantity to 4
        pdp.setQuantity("4");
        //7. Click 'Add to cart' button
        pdp.clickAddToCart();
        //8. Click 'View Cart' button
        CartPage cp = pdp.clickLinkViewCart();
        //9. Verify that product is displayed in cart page with exact quantity
        Assert.assertEquals(cp.getItemQuantity(1),"4","Quantity Error!");
    }
}
