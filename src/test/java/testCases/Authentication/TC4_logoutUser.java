package testCases.Authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC4_logoutUser extends BaseClass {

    @Test(groups = {"Authentication","Login_Logout"})
    public void testLogout(){
        logger.info("***** testCases.Authentication.TC4_logoutUser *****");
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
        hp = rlp.login(TestData.EMAIL,TestData.PASSWORD);
        //8. Verify that 'Logged in as username' is visible
        //9. Click 'Logout' button
        Assert.assertTrue(hp.isLinkLoginExist());
        rlp = hp.logout();
        //10. Verify that user is navigated to login page
        Assert.assertTrue(rlp.checkNavigatedLoginPage());
    }
}
