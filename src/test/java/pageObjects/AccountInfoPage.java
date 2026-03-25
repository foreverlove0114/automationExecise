package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountInfoPage extends BasePage{
    public AccountInfoPage(WebDriver driver) {
        super(driver);
    }

    // 统一使用 By 定位器
    private final By accountInfoTitle = By.xpath("//b[normalize-space()='Enter Account Information']");
    private final By genderRadio = By.xpath("//input[@id='id_gender1']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By daySelect = By.xpath("//select[@id='days']");
    private final By monthSelect = By.xpath("//select[@id='months']");
    private final By yearSelect = By.xpath("//select[@id='years']");
    private final By checkboxNewsletter = By.xpath("//input[@id='newsletter']");
    private final By checkboxOffer = By.xpath("//input[@id='optin']");
    private final By firstNameInput = By.xpath("//input[@id='first_name']");
    private final By lastNameInput = By.xpath("//input[@id='last_name']");
    private final By companyInput = By.xpath("//input[@id='company']");
    private final By address1Input = By.xpath("//input[@id='address1']");
    private final By address2Input = By.xpath("//input[@id='address2']");
    private final By countrySelect = By.xpath("//select[@id='country']");
    private final By stateInput = By.xpath("//input[@id='state']");
    private final By cityInput = By.xpath("//input[@id='city']");
    private final By zipcodeInput = By.xpath("//input[@id='zipcode']");
    private final By mobileInput = By.xpath("//input[@id='mobile_number']");
    private final By buttonCreateAccount = By.xpath("//button[normalize-space()='Create Account']");

    public boolean isEnterAccountInfoTitleVisible(){
        return isElementPresent(accountInfoTitle);
    }

    public void fillInAccountInfo(String password, String day, String month, String year){
        // Radio button 可能需要 JS 点击，因为某些网站的自定义样式
        clickElement(genderRadio);
        sendKeysToElement(passwordInput, password);
        selectByVisibleText(daySelect, day);
        selectByVisibleText(monthSelect, month);
        selectByVisibleText(yearSelect, year);
        // Checkbox 优先使用普通点击
        clickElement(checkboxNewsletter);
        clickElement(checkboxOffer);
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
        sendKeysToElement(firstNameInput, firstname);
        sendKeysToElement(lastNameInput, lastname);
        sendKeysToElement(companyInput, company);
        sendKeysToElement(address1Input, address1);
        sendKeysToElement(address2Input, address2);
        selectByVisibleText(countrySelect, country);
        sendKeysToElement(stateInput, state);
        sendKeysToElement(cityInput, city);
        sendKeysToElement(zipcodeInput, zipcode);
        sendKeysToElement(mobileInput, mobile);
    }

    public AccountCreatedPage clickCreateAccountButton(){
        clickElement(buttonCreateAccount);
        return new AccountCreatedPage(driver);
    }

    // 简化版填写账户信息（仅密码）
    public void fillAccountInfo(String password){
        sendKeysToElement(passwordInput, password);
    }

    // 别名方法，方便调用
    public AccountCreatedPage clickCreateAccount(){
        return clickCreateAccountButton();
    }
}
