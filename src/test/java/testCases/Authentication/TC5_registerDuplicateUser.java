package testCases.Authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC5_registerDuplicateUser extends BaseClass {

    @Test(groups = {"Authentication","Registration"})
    public void testRegisterDuplicateUser(){
        logger.info("***** testCases.Authentication.TC5_registerDuplicateUser *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        //4. Click on 'Signup / Login' button
        RegisterLoginPage rlp = hp.clickSignupLogin();
        //5. Verify 'New User Signup!' is visible
        Assert.assertTrue(rlp.isSignupTitleVisible());
        logger.info("***** Navigated to RegisterLoginPage *****");
        //6. Enter name and already registered email address
        rlp.enterNameAndEmail(TestData.FIRST_NAME,TestData.EMAIL);
        //7. Click 'Signup' button
        rlp.clickSignupButton();
        //8. Verify error 'Email Address already exist!' is visible
        Assert.assertTrue(rlp.isDuplicateUserAlertPresent());
    }
}
