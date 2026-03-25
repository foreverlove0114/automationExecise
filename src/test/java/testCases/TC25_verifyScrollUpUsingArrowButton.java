package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC25_verifyScrollUpUsingArrowButton extends BaseClass {

    @Test
    public void testVerifyScrollUpUsingArrowButton(){
        logger.info("***** Starting TC25_verifyScrollUpUsingArrowButton *****");
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
        
        //6. Click on arrow at bottom right side to move upward
        hp.clickScrollUpArrow();
        logger.info("***** Clicked scroll up arrow button *****");
        
        //7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
        Assert.assertTrue(hp.isHomePageTitleVisible(), "Home page title should be visible after scrolling up");
        logger.info("***** Page scrolled up successfully, home page title is visible *****");
    }
}
