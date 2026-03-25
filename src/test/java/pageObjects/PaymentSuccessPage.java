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

    public void clickDownloadInvoice(){
        By btnDownloadInvoice = By.id("download_invoice_button");
        if(isElementPresent(btnDownloadInvoice)){
            clickElement(btnDownloadInvoice);
        }
    }

    public HomePage clickContinueShoppingFromSuccess(){
        By btnContinue = By.xpath("//a[normalize-space()='Continue Shopping']");
        clickElement(btnContinue);
        return new HomePage(driver);
    }
}
