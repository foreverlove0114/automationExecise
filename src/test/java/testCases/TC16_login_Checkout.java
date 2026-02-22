package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.OrderPage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC16_login_Checkout extends BaseClass {

    @Test
    public void testLoginCheckout(){
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Click 'Signup / Login' button
        RegisterLoginPage rlp = hp.clickSignupLogin();
        //5. Fill email, password and click 'Login' button
        hp = rlp.login(TestData.EMAIL,TestData.PASSWORD);
        //6. Verify 'Logged in as username' at top
        Assert.assertTrue(hp.isLinkLoginExist());
        //7. Add products to cart
        //8. Click 'Cart' button
        //9. Verify that cart page is displayed
        proceedToCheckOut();
        //10. Click Proceed To Checkout
        CartPage cp = new CartPage(getDriver());
        OrderPage op = cp.proceedToCheckout();
        //11. Verify Address Details and Review Your Order
        /*12. Enter description in comment text area and click 'Place Order'
        13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        14. Click 'Pay and Confirm Order' button
        15. Verify success message 'Your order has been placed successfully!'*/
        proceedPayment();
    }
}
