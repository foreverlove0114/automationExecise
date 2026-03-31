package testCases;

import datamodels.ContactUsData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.JsonDataProvider;

import java.io.File;

/**
 * Data-driven test for Contact Us form
 * Tests multiple scenarios with different form data
 */
public class TC6_contactUsForm_DataDriven extends BaseClass {

    /**
     * Test Contact Us form with different data sets
     * This test will run 5 times with different scenarios:
     * 1. Valid Contact Form Submission with attachment
     * 2. Contact Form - Complaint without attachment
     * 3. Contact Form - Feedback with screenshot
     * 4. Contact Form - Empty Message (negative test)
     * 5. Contact Form - Invalid Email (negative test)
     *
     * @param contactData Test data from JSON file
     */
    @Test(dataProvider = "contactUsDataProvider", dataProviderClass = JsonDataProvider.class,
          groups = {"ContactUs", "DataDriven"})
    public void fillContactUsFormWithDataProvider(ContactUsData contactData) {
        logger.info("***** Starting: " + contactData.getTestName() + " *****");
        logger.info("Expected Result: " + contactData.getExpectedResult());

        HomePage hp = new HomePage(getDriver());
        
        // Step 1: Verify home page is visible
        Assert.assertTrue(hp.isHomePageVisible(), "Home page should be visible");
        logger.info("***** HomePage Visible *****");
        
        // Step 2: Click on 'Contact Us' button
        ContactUsPage cp = hp.clickContactUs();
        
        // Step 3: Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(cp.checkWhetherGetInTouchPresent(), 
                "Get In Touch text should be present");
        logger.info("***** Text Get In Touch Present *****");
        
        // Step 4: Fill form with test data
        cp.fillInfoAndMsg(
                contactData.getName(),
                contactData.getEmail(),
                contactData.getSubject(),
                contactData.getMessage()
        );
        
        // Step 5: Upload file if specified
        String filePath = "";
        if (contactData.getAttachmentFile() != null && !contactData.getAttachmentFile().isEmpty()) {
            filePath = System.getProperty("user.dir") + "/src/test/java/utilities/" + 
                      contactData.getAttachmentFile();
            File attachmentFile = new File(filePath);
            if (attachmentFile.exists()) {
                cp.uploadFileAndSubmit(filePath);
                logger.info("***** File uploaded: " + contactData.getAttachmentFile() + " *****");
            } else {
                // If file doesn't exist, submit without attachment
                logger.warn("Attachment file not found: " + filePath);
                cp.uploadFileAndSubmit("");
            }
        } else {
            // Submit without attachment
            cp.uploadFileAndSubmit("");
        }
        
        // Step 6: Handle alert
        cp.handleAlertWithClickOK();
        
        // Step 7: Verify result based on expected outcome
        boolean isSubmitted = cp.isFormSubmittedSuccessfully();
        
        if ("success".equals(contactData.getExpectedResult())) {
            Assert.assertTrue(isSubmitted, 
                    "Form should be submitted successfully for: " + contactData.getTestName());
            logger.info("***** Form Submitted Successfully *****");
            
            // Navigate back to home page
            hp = cp.navigateToHomePageAndVerify();
            Assert.assertTrue(hp.isHomePageVisible(), "Should return to home page");
            logger.info("***** Returned to HomePage *****");
            
        } else {
            // For negative test cases
            Assert.assertFalse(isSubmitted, 
                    "Form should NOT be submitted for: " + contactData.getTestName());
            logger.info("***** Form Submission Failed as Expected *****");
        }
        
        logger.info("***** Completed: " + contactData.getTestName() + " *****");
    }
}
