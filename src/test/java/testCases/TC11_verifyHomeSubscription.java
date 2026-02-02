package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.TestData;

public class TC11_verifyHomeSubscription extends BaseClass {

    @Test
    public void verifyCartSubscription(){
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Click 'Cart' button
        CartPage cp = hp.navigateToCart();
        cp.scrollToSubscription();
        Assert.assertTrue(cp.isTextSubscriptionPresent());
        cp.subscribe(TestData.EMAIL);
        Assert.assertTrue(cp.checkSubscription());
    }
}
