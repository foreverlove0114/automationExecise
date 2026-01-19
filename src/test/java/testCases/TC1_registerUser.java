package testCases;

import org.testng.annotations.Test;
import pageObjects.*;
import utilities.TestData;

public class TC1_registerUser extends testBase.BaseClass{

    @Test
    public void register_user(){
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        System.out.println(hp.isHomePageVisible());
        logger.info("***** Starting TC1_registerUser *****");
        //4. Click on 'Signup / Login' button
        hp.clickSignupLogin();
        //5. Verify 'New User Signup!' is visible
        RegisterLoginPage rlp = new RegisterLoginPage(getDriver());
        System.out.println(rlp.isSignupTitleVisible());
        logger.info("***** Navigated to RegisterLoginPage *****");
        //6. Enter name and email address
        rlp.enterNameAndEmail(randonString(),randomAlphaNumeric() + "gmail.com");
        //7. Click 'Signup' button
        rlp.clickSignupButton();
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        AccountInfoPage aip = new AccountInfoPage(getDriver());
        System.out.println(aip.isEnterAccountInfoTitleVisible());
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
        aip.clickCreateAccountButton();
        //14. Verify that 'ACCOUNT CREATED!' is visible
        AccountCreatedPage acp = new AccountCreatedPage(getDriver());
        System.out.println(acp.isAccountCreatedTitleVisible());
        logger.info("***** Navigated to AccountCreatedPage *****");
        //15. Click 'Continue' button
        acp.clickButtonContinue();
        //16. Verify that 'Logged in as username' is visible
        //17. Click 'Delete Account' button
        HomePage hp1 = new HomePage(getDriver());
        if(hp1.isLinkLoginExist()){
            hp1.clickDeleteAccount();
        }
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        AccountDeletedPage adp = new AccountDeletedPage(getDriver());
        System.out.println(adp.isAccountDeletedTitleVisible());
        logger.info("***** Navigated to AccountDeletedPage *****");
        adp.clickButtonContinue();
    }
}
