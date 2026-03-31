package testCases.Authentication;

import datamodels.LoginData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.JsonDataProvider;

/**
 * Data-driven test for user login
 * Tests multiple login scenarios: valid, invalid email, wrong password, non-existent user
 */
public class TC2_loginValidUser_DataDriven extends BaseClass {

    /**
     * Test login with different credentials from JSON data file
     * This test will run 5 times with different login scenarios:
     * 1. Valid Login
     * 2. Invalid Email Format
     * 3. Wrong Password
     * 4. Non-existent User
     * 5. Empty Credentials
     *
     * @param loginData Test data containing email, password, and expected result
     */
    @Test(dataProvider = "loginDataProvider", dataProviderClass = JsonDataProvider.class,
          groups = {"Authentication", "Login_Logout", "DataDriven"})
    public void testLoginWithDataProvider(LoginData loginData) {
        logger.info("***** Starting: " + loginData.getTestName() + " *****");
        logger.info("Description: " + loginData.getDescription());
        logger.info("Expected Result: " + loginData.getExpectedResult());

        HomePage hp = new HomePage(getDriver());
        
        // Step 1: Verify home page is visible
        Assert.assertTrue(hp.isHomePageVisible(), "Home page should be visible");
        
        // Step 2: Navigate to Signup/Login page
        RegisterLoginPage rlp = hp.clickSignupLogin();
        Assert.assertTrue(rlp.isSignupTitleVisible(), "Login page should be visible");
        logger.info("***** Navigated to RegisterLoginPage *****");
        
        // Step 3: Attempt login with provided credentials
        rlp.login(loginData.getEmail(), loginData.getPassword());
        
        // Step 4: Verify login result based on expected outcome
        boolean isLoggedIn = hp.isLinkLoginExist();
        
        if ("success".equals(loginData.getExpectedResult())) {
            Assert.assertTrue(isLoggedIn, 
                    "User should be logged in successfully for: " + loginData.getTestName());
            logger.info("***** Login Successful *****");
            
            // Logout for cleanup
            hp.logout();
            logger.info("***** Logged out successfully *****");
            
        } else {
            // For negative test cases, verify login failed
            Assert.assertFalse(isLoggedIn, 
                    "User should NOT be logged in for: " + loginData.getTestName());
            logger.info("***** Login Failed as Expected *****");
            
            // Verify error message is displayed
            Assert.assertTrue(rlp.isLoginErrorVisible() || rlp.isSignupTitleVisible(),
                    "Error message or login form should be visible");
        }
        
        logger.info("***** Completed: " + loginData.getTestName() + " *****");
    }
}
