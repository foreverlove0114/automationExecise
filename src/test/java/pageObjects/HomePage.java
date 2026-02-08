package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[contains(text(),\"Full-Fledged practice\")]")
    private WebElement HomePageTitle;

    @FindBy(xpath = " //a[normalize-space()='Signup / Login']")
    private WebElement Signup_Login_Link;

    @FindBy(partialLinkText = "Logged in")
    private WebElement linkCheckLogin;

    @FindBy(xpath = "//a[normalize-space()='Delete Account']")
    private WebElement linkDeleteAccount;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement linkLogout;

    @FindBy(xpath = "//a[normalize-space()='Contact us']")
    private WebElement linkContactUs;

    @FindBy(xpath = "//div[@class='item active']//button[@type='button'][normalize-space()='Test Cases']")
    private WebElement buttonTestCases;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement linkProducts;

    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    private WebElement textSubscription;

    @FindBy(xpath = "//input[@id='susbscribe_email']")
    private WebElement emailSubscriptionInput;

    @FindBy(xpath = "//button[@id='subscribe']")
    private WebElement clickSubscription;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    private WebElement alertSubscribeSuccess;

    @FindBy(xpath = "//a[normalize-space()='Cart']")
    private WebElement linkCart;

    @FindBy(xpath = "//div[@class='choose']//a")
    private List<WebElement> allViewProductBtns;

    public boolean isHomePageVisible(){
        return isElementPresent(HomePageTitle);
    }

    public RegisterLoginPage clickSignupLogin(){
        clickElementJS(Signup_Login_Link);
        return new RegisterLoginPage(driver);
    }

    public boolean isLinkLoginExist(){
        return isElementPresent(linkCheckLogin);
    }

    public AccountDeletedPage clickDeleteAccount(){
        clickElementJS(linkDeleteAccount);
        return new AccountDeletedPage(driver);
    }

    public RegisterLoginPage logout(){
        clickElementJS(linkLogout);
        return new RegisterLoginPage(driver);
    }

    public ContactUsPage clickContactUs(){
        clickElementJS(linkContactUs);
        return new ContactUsPage(driver);
    }

    public TestCasesPage clickNavigateToTestCasesPage(){
        clickElementJS(buttonTestCases);
        return new TestCasesPage(driver);
    }

    public ProductsPage clickNavigateToProductPage(){
        clickElementJS(linkProducts);
        return new ProductsPage(driver);
    }

    public boolean isTextSubscriptionPresent(){
        return isElementPresent(textSubscription);
    }

    public void scrollToSubscription(){
        scrollDownUntilText(textSubscription);
    }

    public void subscribe(String text){
        sendKeysToElement(emailSubscriptionInput,text);
        clickElementJS(clickSubscription);
    }

    public boolean checkSubscription(){
        return isElementPresent(alertSubscribeSuccess);
    }

    public CartPage navigateToCart(){
        clickElementJS(linkCart);
        return new CartPage(driver);
    }

    public ProductDetailsPage clickRandomViewProduct(){
        Random random = new Random();
        // 2. 使用 Random 类生成随机索引
        int randomIndex = random.nextInt(allViewProductBtns.size());
        // 3. 获取随机出的那个按钮
        WebElement randomBtn = allViewProductBtns.get(randomIndex);
        clickElementJS(randomBtn);

        return new ProductDetailsPage(driver);
    }


}
