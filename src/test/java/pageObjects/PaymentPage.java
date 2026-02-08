package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage{

    private final By textNameOnCard = By.name("name_on_card");
    private final By textCartNum = By.name("card_number");
    private final By textCVC = By.name("cvc");
    private final By textExpirationMM = By.name("expiry_month");
    private final By getTextExpirationYYYY = By.name("expiry_year");
    private final By btnConfirmOrder = By.id("submit");


    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public PaymentSuccessPage makePayment(String nameOnCard, String CartNum, String CVC, String ExpirationMM,String ExpirationYYYY){
        wait.until(ExpectedConditions.urlContains("payment"));
        driver.navigate().refresh();
        sendKeysToElement(textNameOnCard,nameOnCard);
        sendKeysToElement(textCartNum,CartNum);
        sendKeysToElement(textCVC,CVC);
        sendKeysToElement(textExpirationMM,ExpirationMM);
        sendKeysToElement(getTextExpirationYYYY,ExpirationYYYY);
        clickElement(btnConfirmOrder);
        return new PaymentSuccessPage(driver);
    }
}
