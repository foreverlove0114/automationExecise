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
        HomePage hp = new HomePage(getDriver());
        //4. Add products to cart
        hp.addProductToCartByIndex(1);
        hp.clickContinueShopping();
        hp.addProductToCartByIndex(2);
        //5. Click 'Cart' button
        CartPage cp = hp.clickLinkViewCart();
        //6. Verify that cart page is displayed
        Assert.assertTrue(cp.isCheckoutButtonExist());
        //7. Click Proceed To Checkout
        cp.proceedToCheckout();
        //8. Click 'Register / Login' button
        RegisterLoginPage rlp = cp.jumpRegisterLogin();
        //9. Fill all details in Signup and create account
        rlp.enterNameAndEmail(randonString(),randomAlphaNumeric() + "gmail.com");
        AccountInfoPage aip =rlp.clickSignupButton();
        //10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        aip.fillInAccountInfo(TestData.PASSWORD,TestData.DAY,TestData.MONTH,TestData.YEAR);
        aip.fillInAddressInfo(TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.COMPANY,
                TestData.ADDRESS1,
                TestData.ADDRESS2,
                TestData.COUNTRY,
                TestData.STATE,
                TestData.CITY,
                TestData.ZIPCODE,
                randomNumber());
        AccountCreatedPage acp = aip.clickCreateAccountButton();
        Assert.assertTrue(acp.isAccountCreatedTitleVisible());
        Assert.assertTrue(acp.isAccountCreatedTitleVisible());
        hp = acp.clickButtonContinue();
        //11. Verify ' Logged in as username' at top
        Assert.assertTrue(hp.isLinkLoginExist());
        //12.Click 'Cart' button
        cp = hp.nav.clickCart();
        //13. Click 'Proceed To Checkout' button
        OrderPage op = cp.proceedToCheckout();
        //14. Verify Address Details and Review Your Order
        Assert.assertTrue(op.isAddressAndOrderInfoPresent());
        //15. Enter description in comment text area and click 'Place Order'
        op.inputTextIntoComment("Please take care of the products");
        PaymentPage pp = op.clickPlaceOrder();
        //16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        //17. Click 'Pay and Confirm Order' button
        PaymentSuccessPage psp = pp.makePayment(TestData.nameOnCard,TestData.cardNum, TestData.cvc,TestData.ExpirationMM,TestData.ExpirationYYYY);
        //18. Verify success message 'Your order has been placed successfully!'
        Assert.assertTrue(psp.isSuccessAlertExist());
    }
}
