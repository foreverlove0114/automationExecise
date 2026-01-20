package testCases.Authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC3_loginInvalidUser extends BaseClass {

    @Test(groups = {"Authentication","Login_Logout"})
    public void testLoginAsInvalidUser(){
        logger.info("***** Starting TC3_LoginInvalidUser *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        //4. Click on 'Signup / Login' button
        RegisterLoginPage rlp = hp.clickSignupLogin();
        //5. Verify 'New User Signup!' is visible
        Assert.assertTrue(rlp.isSignupTitleVisible());
        logger.info("***** Navigated to RegisterLoginPage *****");
        //6. Enter correct email address and password
        //7. Click 'login' button
        rlp.login(TestData.INVALID_EMAIL,TestData.INVALID_PASSWORD);
        //8. Verify error 'Your email or password is incorrect!' is visible
        Assert.assertTrue(rlp.isAlertPresent());
    }
}

