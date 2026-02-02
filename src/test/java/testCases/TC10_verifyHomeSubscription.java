package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.TestData;

public class TC10_verifyHomeSubscription extends BaseClass {

    @Test
    public void testVerifySubscription(){
        //3. Verify that home page is visible successfully
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Scroll down to footer
        //5. Verify text 'SUBSCRIPTION'
        hp.scrollToSubscription();
        Assert.assertTrue(hp.isTextSubscriptionPresent());
        //6. Enter email address in input and click arrow button
        hp.subscribe(TestData.EMAIL);
        Assert.assertTrue(hp.checkSubscription());
        System.out.println("Result: " + hp.checkSubscription());
    }
}
