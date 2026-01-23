package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.TestCasesPage;
import testBase.BaseClass;

public class TC7_verifyTestCasesPage extends BaseClass {

    @Test
    public void testVerifyTestCasesPage(){
        logger.info("***** testCases.Authentication.TC7_verifyTestCasesPage *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** HomePage Visible *****");
        //4. Click on 'Test Cases' button
        TestCasesPage tcp = hp.clickNavigateToTestCasesPage();
        //5. Verify user is navigated to test cases page successfully
        Assert.assertTrue(tcp.isHeadingTestCasesPagePresent());
        logger.info("***** Page Navigated To TestCasesPage *****");
    }
}
