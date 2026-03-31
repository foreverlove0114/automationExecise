package testCases.Authentication;

import datamodels.RegistrationData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.JsonDataProvider;

/**
 * Data-driven test for user registration
 * Uses TestNG DataProvider to run same test with multiple data sets
 */
public class TC1_registerUser_DataDriven extends BaseClass {

    /**
     * Test user registration with different data sets from JSON file
     * This test will run 4 times with different registration data:
     * 1. Valid Registration - Singapore
     * 2. Valid Registration - United States
     * 3. Valid Registration - Canada
     * 4. Invalid Registration - Short Password
     *
     * @param registrationData Test data from JSON file
     */
    @Test(dataProvider = "registrationDataProvider", dataProviderClass = JsonDataProvider.class,
          groups = {"Authentication", "Registration", "DataDriven"})
    public void registerUserWithDataProvider(RegistrationData registrationData) {
        logger.info("***** Starting: " + registrationData.getTestName() + " *****");
        logger.info("Expected Result: " + registrationData.getExpectedResult());

        try {
            HomePage hp = new HomePage(getDriver());
            
            // Step 1: Verify home page is visible
            Assert.assertTrue(hp.isHomePageVisible(), "Home page should be visible");
            
            // Step 2: Navigate to Signup/Login page
            RegisterLoginPage rlp = hp.clickSignupLogin();
            Assert.assertTrue(rlp.isSignupTitleVisible(), "Signup title should be visible");
            logger.info("***** Navigated to RegisterLoginPage *****");
            
            // Step 3: Enter name and email (generate unique email for each test)
            String uniqueEmail = System.currentTimeMillis() + "@test.com";
            rlp.enterNameAndEmail(registrationData.getAddressInfo().getFirstName(), uniqueEmail);
            
            // Step 4: Click Signup button
            AccountInfoPage aip = rlp.clickSignupButton();
            Assert.assertTrue(aip.isEnterAccountInfoTitleVisible(), 
                    "Account info title should be visible");
            logger.info("***** Navigated to AccountInfoPage *****");
            
            // Step 5: Fill account information from test data
            aip.fillInAccountInfo(
                    registrationData.getAccountInfo().getPassword(),
                    registrationData.getAccountInfo().getDay(),
                    registrationData.getAccountInfo().getMonth(),
                    registrationData.getAccountInfo().getYear()
            );
            
            // Step 6: Fill address information from test data
            aip.fillInAddressInfo(
                    registrationData.getAddressInfo().getFirstName(),
                    registrationData.getAddressInfo().getLastName(),
                    registrationData.getAddressInfo().getCompany(),
                    registrationData.getAddressInfo().getAddress1(),
                    registrationData.getAddressInfo().getAddress2(),
                    registrationData.getAddressInfo().getCountry(),
                    registrationData.getAddressInfo().getState(),
                    registrationData.getAddressInfo().getCity(),
                    registrationData.getAddressInfo().getZipcode(),
                    registrationData.getAddressInfo().getMobile()
            );
            
            // Step 7: Click Create Account button
            AccountCreatedPage acp = aip.clickCreateAccountButton();
            
            // Step 8: Verify result based on expected outcome
            boolean isAccountCreated = acp.isAccountCreatedTitleVisible();
            
            if ("success".equals(registrationData.getExpectedResult())) {
                Assert.assertTrue(isAccountCreated, 
                        "Account should be created successfully for: " + registrationData.getTestName());
                logger.info("***** Account Created Successfully *****");
                
                // Continue to delete account for cleanup
                hp = acp.clickButtonContinue();
                Assert.assertTrue(hp.isLinkLoginExist(), "User should be logged in");
                
                AccountDeletedPage adp = hp.clickDeleteAccount();
                Assert.assertTrue(adp.isAccountDeletedTitleVisible(), 
                        "Account should be deleted successfully");
                logger.info("***** Account Deleted Successfully *****");
                
            } else {
                // For negative test cases, we expect failure
                Assert.assertFalse(isAccountCreated, 
                        "Account should NOT be created for: " + registrationData.getTestName());
                logger.info("***** Registration Failed as Expected *****");
            }
            
        } catch (Exception e) {
            if ("failure".equals(registrationData.getExpectedResult())) {
                logger.info("***** Test failed as expected for negative case: " + 
                        registrationData.getTestName() + " *****");
            } else {
                throw e;
            }
        }
        
        logger.info("***** Completed: " + registrationData.getTestName() + " *****");
    }
}
