package testCases.Authentication;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utilities.TestData;

public class TC1_registerUser extends testBase.BaseClass{

    @Test(groups = {"Authentication","Registration"})
    public void register_user(){
        logger.info("***** Starting TC1_registerUser *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        //4. Click on 'Signup / Login' button
        RegisterLoginPage rlp = hp.clickSignupLogin();
        //5. Verify 'New User Signup!' is visible
        Assert.assertTrue(rlp.isSignupTitleVisible());
        logger.info("***** Navigated to RegisterLoginPage *****");
        //6. Enter name and email address
        rlp.enterNameAndEmail(randonString(),randomAlphaNumeric() + "gmail.com");
        //7. Click 'Signup' button
        AccountInfoPage aip = rlp.clickSignupButton();
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Assert.assertTrue(aip.isEnterAccountInfoTitleVisible());
        logger.info("***** Navigated to AccountInfoPage *****");
        //9. Fill details: Title, Name, Email, Password, Date of birth
        //10. Select checkbox 'Sign up for our newsletter!'
        //11. Select checkbox 'Receive special offers from our partners!'
        aip.fillInAccountInfo(TestData.PASSWORD,TestData.DAY,TestData.MONTH,TestData.YEAR);
        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        aip.fillInAddressInfo(TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.COMPANY,
                TestData.ADDRESS1,
                TestData.ADDRESS2,
                TestData.COUNTRY,
                TestData.STATE,
                TestData.CITY,
                TestData.ZIPCODE,
                randomNumber());
        //13. Click 'Create Account button'
        AccountCreatedPage acp = aip.clickCreateAccountButton();
        //14. Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertTrue(acp.isAccountCreatedTitleVisible());
        logger.info("***** Navigated to AccountCreatedPage *****");
        //15. Click 'Continue' button
        hp = acp.clickButtonContinue();
        //16. Verify that 'Logged in as username' is visible
        //17. Click 'Delete Account' button
        Assert.assertTrue(hp.isLinkLoginExist());
        AccountDeletedPage adp = hp.clickDeleteAccount();
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        Assert.assertTrue(adp.isAccountDeletedTitleVisible());
        logger.info("***** Navigated to AccountDeletedPage *****");
        adp.clickButtonContinue();
    }
}
