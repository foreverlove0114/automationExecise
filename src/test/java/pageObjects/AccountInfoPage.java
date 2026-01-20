package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountInfoPage extends BasePage{
    public AccountInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//b[normalize-space()='Enter Account Information']")
    private WebElement accountInfoTitle;

    @FindBy(xpath = "//input[@id='id_gender1']")
    private WebElement genderRadio;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//select[@id='days']")
    private WebElement daySelect;

    @FindBy(xpath = "//select[@id='months']")
    private WebElement monthSelect;

    @FindBy(xpath = "//select[@id='years']")
    private WebElement yearSelect;

    @FindBy(xpath = "//input[@id='newsletter']")
    private WebElement checkboxNewsletter;

    @FindBy(xpath = "//input[@id='optin']")
    private WebElement checkboxOffer;

    @FindBy(xpath = "//input[@id='first_name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last_name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='company']")
    private WebElement companyInput;

    @FindBy(xpath = "//input[@id='address1']")
    private WebElement address1Input;

    @FindBy(xpath = "//input[@id='address2']")
    private WebElement address2Input;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement countrySelect;

    @FindBy(xpath = "//input[@id='state']")
    private WebElement stateInput;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityInput;

    @FindBy(xpath = "//input[@id='zipcode']")
    private WebElement zipcodeInput;

    @FindBy(xpath = "//input[@id='mobile_number']")
    private WebElement mobileInput;

    @FindBy(xpath = "//button[normalize-space()='Create Account']")
    private WebElement buttonCreateAccount;

    public boolean isEnterAccountInfoTitleVisible(){
        return isElementPresent(accountInfoTitle);
    }

    public void fillInAccountInfo(String password,String day,String month,String year){
        clickElementJS(genderRadio);
        sendKeysToElement(passwordInput,password);
        selectByVisibleText(daySelect,day);
        selectByVisibleText(monthSelect,month);
        selectByVisibleText(yearSelect,year);
        clickElementJS(checkboxNewsletter);
        clickElementJS(checkboxOffer);
    }

    public void fillInAddressInfo(
            String firstname,
            String lastname,
            String company,
            String address1,
            String address2,
            String country,
            String state,
            String city,
            String zipcode,
            String mobile){
        sendKeysToElement(firstNameInput,firstname);
        sendKeysToElement(lastNameInput,lastname);
        sendKeysToElement(companyInput,company);
        sendKeysToElement(address1Input,address1);
        sendKeysToElement(address2Input,address2);
        selectByVisibleText(countrySelect,country);
        sendKeysToElement(stateInput,state);
        sendKeysToElement(cityInput,city);
        sendKeysToElement(zipcodeInput,zipcode);
        sendKeysToElement(mobileInput,mobile);
    }

    public AccountCreatedPage clickCreateAccountButton(){
        clickElementJS(buttonCreateAccount);
        return new AccountCreatedPage(driver);
    }
}
