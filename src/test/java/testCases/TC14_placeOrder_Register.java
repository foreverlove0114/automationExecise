package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.TestData;

public class TC14_placeOrder_Register extends BaseClass {

    @Test
    public void testPlaceOrderAndRegister(){
        initializeHomePage();
        proceedToCheckOut();
        //8. Click 'Register / Login' button
        CartPage cp = new CartPage(getDriver());
        cp.proceedToCheckout();
        RegisterLoginPage rlp = cp.jumpRegisterLogin();
        //9. Fill all details in Signup and create account
        fillInRegister();
        //12.Click 'Cart' button
        HomePage hp = new HomePage(getDriver());
        cp = hp.nav.clickCart();
        //13. Click 'Proceed To Checkout' button
        OrderPage op = cp.proceedToCheckout();
        //14. Verify Address Details and Review Your Order
        proceedPayment();
    }
}
