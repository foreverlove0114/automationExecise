package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.components.CategoryBrandsComponent;
import pageObjects.components.FooterComponent;
import pageObjects.components.NavComponent;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
        this.nav = new NavComponent(driver);
        this.footer = new FooterComponent(driver);
        this.category = new CategoryBrandsComponent(driver);
    }

    // 统一使用 By 定位器
    private final By HomePageTitle = By.xpath("//h2[contains(text(),'Full-Fledged practice')]");
    private final By Signup_Login_Link = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By linkCheckLogin = By.partialLinkText("Logged in");
    private final By linkDeleteAccount = By.xpath("//a[normalize-space()='Delete Account']");
    private final By linkLogout = By.xpath("//a[normalize-space()='Logout']");
    private final By linkContactUs = By.xpath("//a[normalize-space()='Contact us']");
    private final By buttonTestCases = By.xpath("//div[@class='item active']//button[@type='button'][normalize-space()='Test Cases']");
    private final By linkProducts = By.xpath("//a[@href='/products']");
    private final By textSubscription = By.xpath("//h2[normalize-space()='Subscription']");
    private final By emailSubscriptionInput = By.xpath("//input[@id='susbscribe_email']");
    private final By clickSubscription = By.xpath("//button[@id='subscribe']");
    private final By alertSubscribeSuccess = By.xpath("//div[@class='alert-success alert']");
    private final By linkCart = By.xpath("//a[normalize-space()='Cart']");
    private final By allViewProductBtns = By.xpath("//div[@class='choose']//a");
    private final By headingCategory = By.xpath("//h2[normalize-space()='Category']");
    private final By categoryWomen = By.xpath("//a[normalize-space()='Women']");
    private final By dressLink = By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]");

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
        List<WebElement> viewBtns = driver.findElements(allViewProductBtns);
        Random random = new Random();
        int randomIndex = random.nextInt(viewBtns.size());
        WebElement randomBtn = viewBtns.get(randomIndex);
        clickElementJS(randomBtn);
        return new ProductDetailsPage(driver);
    }

    public boolean isHeadingCategoryPresent(){
        return isElementPresent(headingCategory);
    }

    public void clickCategoryWomen(){
        clickElementJS(categoryWomen);
    }

    public CategoryProductPage clickDressLink(){
        clickElementJS(dressLink);
        return new CategoryProductPage(driver);
    }
}
