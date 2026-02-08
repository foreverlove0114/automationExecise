package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentSuccessPage extends BasePage{
    private final By successAlert = By.xpath("//p[normalize-space()='Congratulations! Your order has been confirmed!']");

    public PaymentSuccessPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccessAlertExist(){
        return isElementPresent(successAlert);
    }
}
