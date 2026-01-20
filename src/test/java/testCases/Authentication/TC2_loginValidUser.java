package testCases.Authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC2_loginValidUser extends BaseClass {


    @Test(groups = {"Authentication","Login_Logout"})
    public void testLoginAsValidUser(){
        logger.info("***** Starting TC2_loginValidUser *****");
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
        rlp.login(TestData.EMAIL,TestData.PASSWORD);
        //8. Verify that 'Logged in as username' is visible
        Assert.assertTrue(hp.isLinkLoginExist());
        if(hp.isLinkLoginExist()){
            logger.info("***** Login successfully *****");
        }
    }
}

