package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.TestData;

import java.io.File;

public class TC6_contactUsForm extends BaseClass {

    @Test
    public void fillContactUsForm(){
        logger.info("***** testCases.Authentication.TC6_contactUsForm *****");
        HomePage hp = new HomePage(getDriver());
        //3. Verify that home page is visible successfully
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** HomePage Visible *****");
        //4. Click on 'Contact Us' button
        ContactUsPage cp = hp.clickContactUs();
        //5. Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(cp.checkWhetherGetInTouchPresent());
        logger.info("***** Text Get In Touch Present *****");
        //6. Enter name, email, subject and message
        cp.fillInfoAndMsg(TestData.FIRST_NAME,TestData.EMAIL,TestData.SUBJECT,TestData.MESSAGE);
        //7. Upload file
        //8. Click 'Submit' button
        String filePath = System.getProperty("user.dir") + "/src/test/java/utilities/IMG_9234.jpg";
        cp.uploadFileAndSubmit(filePath); // this line want to ask
        //9. Click OK button
        cp.handleAlertWithClickOK();
        //10. Verify success message 'Success! Your details have been submitted successfully.' is visible
        Assert.assertTrue(cp.isFormSubmittedSuccessfully());
        logger.info("***** Form Submitted Successfully *****");
        //11. Click 'Home' button and verify that landed to home page successfully
        hp = cp.navigateToHomePageAndVerify();
        Assert.assertTrue(hp.isHomePageVisible());
        logger.info("***** HomePage Visible *****");
    }
}
