package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.TestData;

public class TC15_register_Checkout extends BaseClass {

    @Test
    public void testRegisterCheckout(){
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Click 'Signup / Login' button
        hp.clickSignupLogin();
        //5. Fill all details in Signup and create account
        fillInRegister();
        //8. Add products to cart
        proceedToCheckOut();
        CartPage cp = new CartPage(getDriver());
        OrderPage op = cp.proceedToCheckout();
        //14. Verify Address Details and Review Your Order
        proceedPayment();
    }
}
