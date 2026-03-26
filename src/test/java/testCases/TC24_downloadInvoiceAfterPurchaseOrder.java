package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountCreatedPage;
import pageObjects.AccountDeletedPage;
import pageObjects.AccountInfoPage;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.OrderPage;
import pageObjects.PaymentPage;
import pageObjects.PaymentSuccessPage;
import pageObjects.ProductsPage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC24_downloadInvoiceAfterPurchaseOrder extends BaseClass {

    @Test
    public void testDownloadInvoiceAfterPurchaseOrder(){
        logger.info("***** Starting TC24_downloadInvoiceAfterPurchaseOrder *****");
        HomePage hp = new HomePage(getDriver());
        
        //1. Launch browser - handled by @BeforeMethod
        //2. Navigate to url 'http://automationexercise.com' - handled by @BeforeMethod
        
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** Home page is visible *****");
        
        //4. Add products to cart
        ProductsPage pp = hp.clickNavigateToProductPage();
        pp.addProductToCartByIndex(1);
        logger.info("***** Added product to cart *****");
        
        //5. Click 'Cart' button
        CartPage cp = pp.clickLinkViewCart();
        logger.info("***** Navigated to Cart page *****");
        
        //6. Verify that cart page is displayed
        Assert.assertTrue(cp.countItemsInCart() >= 1, "Cart should have at least 1 item");
        logger.info("***** Cart page is displayed with items *****");
        
        //7. Click Proceed To Checkout
        OrderPage op = cp.proceedToCheckout();
        logger.info("***** Clicked Proceed To Checkout *****");
        
        //8. Click 'Register / Login' button
        RegisterLoginPage rlp = op.jumpToRegisterLogin();
        logger.info("***** Navigated to Register / Login page *****");
        
        //9. Fill all details in Signup and create account
        rlp.enterNameAndEmail(TestData.FIRST_NAME, TestData.EMAIL_CREATE);
        AccountInfoPage aip = rlp.clickSignupButton();
        logger.info("***** Entered signup details *****");
        
        //10. Complete account creation
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
        AccountCreatedPage acp = aip.clickCreateAccount();
        
        //11. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertTrue(acp.isAccountCreatedTitleVisible());
        logger.info("***** Account created successfully *****");
        hp = acp.clickButtonContinue();
        
        //12. Verify ' Logged in as username' at top
        Assert.assertTrue(hp.isLinkLoginExist());
        logger.info("***** Logged in as user *****");
        
        //13. Click 'Cart' button
        cp = hp.navigateToCart();
        logger.info("***** Navigated to Cart page *****");
        
        //14. Click 'Proceed To Checkout' button
        op = cp.proceedToCheckout();
        logger.info("***** Clicked Proceed To Checkout *****");
        
        //15. Verify Address Details and Review Your Order
        Assert.assertTrue(op.isAddressAndOrderInfoPresent(), "Address Details should be present");
        logger.info("***** Address Details and Review Order are visible *****");
        
        //16. Enter description in comment text area and click 'Place Order'
        op.inputTextIntoComment("This is a test order");
        PaymentPage paymentPage = op.clickPlaceOrder();
        logger.info("***** Placed order successfully *****");
        
        //17. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.makePayment("Test User", "1234567890123456", "123", "12", "2025");
        logger.info("***** Entered payment details *****");
        
        //18. Click 'Pay and Confirm Order' button - handled in makePayment method
        
        //19. Verify success message 'Your order has been placed successfully!'
        PaymentSuccessPage psp = new PaymentSuccessPage(getDriver());
        Assert.assertTrue(psp.isSuccessAlertExist(), "Order success message should be displayed");
        logger.info("***** Order placed successfully *****");
        
        //20. Click 'Download Invoice' button and verify invoice is downloaded successfully
        psp.clickDownloadInvoice();
        logger.info("***** Downloaded invoice successfully *****");
        
        //21. Click 'Continue' button
        HomePage hp2 = psp.clickContinueShoppingFromSuccess();
        
        //22. Click 'Delete Account' button
        AccountDeletedPage adp = hp2.clickDeleteAccount();
        
        //23. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(adp.isAccountDeletedTitleVisible());
        logger.info("***** Account deleted successfully *****");
        adp.clickButtonContinue();
    }
}
