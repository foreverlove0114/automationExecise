package testCases;

import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.OrderPage;
import testBase.BaseClass;

public class TC17_removeProducts extends BaseClass {

    @Test
    public void testRemoveProductsFromCart() throws InterruptedException {
        initializeHomePage();
        //4. Add products to cart
        //5. Click 'Cart' button
        //6. Verify that cart page is displayed
        proceedToCheckOut();
        CartPage cp = new CartPage(getDriver());
        //7. Click 'X' button corresponding to particular product
        cp.removeProductByID(1);
        Thread.sleep(500);
        cp.removeProductByID(2);
        cp.isCartEmptyAlertPresent();
    }
}
