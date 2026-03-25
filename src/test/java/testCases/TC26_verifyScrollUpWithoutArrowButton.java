package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC26_verifyScrollUpWithoutArrowButton extends BaseClass {

    @Test
    public void testVerifyScrollUpWithoutArrowButton(){
        logger.info("***** Starting TC26_verifyScrollUpWithoutArrowButton *****");
        HomePage hp = new HomePage(getDriver());
        
        //1. Launch browser - handled by @BeforeMethod
        //2. Navigate to url 'http://automationexercise.com' - handled by @BeforeMethod
        
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** Home page is visible *****");
        
        //4. Scroll down page to bottom
        hp.scrollToBottom();
        logger.info("***** Scrolled to bottom of page *****");
        
        //5. Verify 'SUBSCRIPTION' is visible
        Assert.assertTrue(hp.isTextSubscriptionPresent(), "Subscription section should be visible at bottom");
        logger.info("***** Subscription section is visible at bottom *****");
        
        //6. Scroll up page to top (without using arrow button)
        hp.scrollToTop();
        logger.info("***** Scrolled to top of page *****");
        
        //7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
        Assert.assertTrue(hp.isHomePageTitleVisible(), "Home page title should be visible after scrolling up");
        logger.info("***** Page scrolled up successfully, home page title is visible *****");
    }
}
