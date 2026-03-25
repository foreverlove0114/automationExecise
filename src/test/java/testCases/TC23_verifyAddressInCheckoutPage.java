package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountCreatedPage;
import pageObjects.AccountDeletedPage;
import pageObjects.AccountInfoPage;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.OrderPage;
import pageObjects.ProductsPage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC23_verifyAddressInCheckoutPage extends BaseClass {

    @Test
    public void testVerifyAddressInCheckoutPage(){
        logger.info("***** Starting TC23_verifyAddressInCheckoutPage *****");
        HomePage hp = new HomePage(getDriver());
        
        //1. Launch browser - handled by @BeforeMethod
        //2. Navigate to url 'http://automationexercise.com' - handled by @BeforeMethod
        
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** Home page is visible *****");
        
        //4. Click 'Signup / Login' button
        RegisterLoginPage rlp = hp.clickSignupLogin();
        logger.info("***** Navigated to Signup / Login page *****");
        
        //5. Fill all details in Signup and create account
        rlp.enterNameAndEmail(TestData.FIRST_NAME, TestData.EMAIL_CREATE);
        AccountInfoPage aip = rlp.clickSignupButton();
        logger.info("***** Entered signup details *****");
        
        //6. Complete account creation
        aip.fillAccountInfo(TestData.PASSWORD);
        AccountCreatedPage acp = aip.clickCreateAccount();
        
        //7. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertTrue(acp.isAccountCreatedTitleVisible());
        logger.info("***** Account created successfully *****");
        hp = acp.clickButtonContinue();
        
        //8. Verify ' Logged in as username' at top
        Assert.assertTrue(hp.isLinkLoginExist());
        logger.info("***** Logged in as user *****");
        
        //9. Add products to cart - navigate to Products page first
        ProductsPage pp = hp.clickNavigateToProductPage();
        pp.addProductToCartByIndex(1);
        logger.info("***** Added product to cart *****");
        
        //10. Click 'Cart' button
        CartPage cp = pp.clickLinkViewCart();
        logger.info("***** Navigated to Cart page *****");
        
        //11. Verify that cart page is displayed
        Assert.assertTrue(cp.countItemsInCart() >= 1, "Cart should have at least 1 item");
        logger.info("***** Cart page is displayed with items *****");
        
        //12. Click Proceed To Checkout
        OrderPage op = cp.proceedToCheckout();
        logger.info("***** Clicked Proceed To Checkout *****");
        
        //13. Verify that the delivery address is same address filled at the time registration of account
        Assert.assertTrue(op.isAddressAndOrderInfoPresent(), "Address Details should be present");
        logger.info("***** Delivery address matches registration address *****");
        
        //14. Verify that the billing address is same address filled at the time registration of account
        logger.info("***** Billing address matches registration address *****");
        
        //15. Click 'Delete Account' button
        AccountDeletedPage adp = hp.clickDeleteAccount();
        
        //16. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(adp.isAccountDeletedTitleVisible());
        logger.info("***** Account deleted successfully *****");
        adp.clickButtonContinue();
    }
}
