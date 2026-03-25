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
    private final By headingRecommendedItem = By.xpath("//h2[normalize-space()='recommended items']");
    
    // 推荐商品轮播图中的第一个产品（当前 active 的 item）
    private final By firstRecommendedProduct = By.xpath("(//div[@class='item active']//div[@class='col-sm-4'])[1]");
    private final By firstRecommendedAddToCartBtn = By.xpath("(//div[@class='item active']//a[@data-product-id])[1]");
    private final By recommendedItemsContainer = By.id("recommended-item-carousel");

    public boolean isHomePageVisible(){
        return isElementPresent(HomePageTitle);
    }

    public RegisterLoginPage clickSignupLogin(){
        clickElement(Signup_Login_Link);
        return new RegisterLoginPage(driver);
    }

    public boolean isLinkLoginExist(){
        return isElementPresent(linkCheckLogin);
    }

    public AccountDeletedPage clickDeleteAccount(){
        clickElement(linkDeleteAccount);
        return new AccountDeletedPage(driver);
    }

    public RegisterLoginPage logout(){
        clickElement(linkLogout);
        return new RegisterLoginPage(driver);
    }

    public ContactUsPage clickContactUs(){
        clickElement(linkContactUs);
        return new ContactUsPage(driver);
    }

    public TestCasesPage clickNavigateToTestCasesPage(){
        clickElement(buttonTestCases);
        return new TestCasesPage(driver);
    }

    public ProductsPage clickNavigateToProductPage(){
        clickElement(linkProducts);
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
        clickElement(clickSubscription);
    }

    public boolean checkSubscription(){
        return isElementPresent(alertSubscribeSuccess);
    }

    public CartPage navigateToCart(){
        clickElement(linkCart);
        return new CartPage(driver);
    }

    public ProductDetailsPage clickRandomViewProduct(){
        List<WebElement> viewBtns = driver.findElements(allViewProductBtns);
        Random random = new Random();
        int randomIndex = random.nextInt(viewBtns.size());
        WebElement randomBtn = viewBtns.get(randomIndex);
        // 使用 JS 点击 WebElement
        js.executeScript("arguments[0].click();", randomBtn);
        return new ProductDetailsPage(driver);
    }

    public boolean isHeadingCategoryPresent(){
        return isElementPresent(headingCategory);
    }

    public void clickCategoryWomen(){
        clickElement(categoryWomen);
    }

    public CategoryProductPage clickDressLink(){
        clickElement(dressLink);
        return new CategoryProductPage(driver);
    }

    public void scrollToRecommendItems(){
        scrollDownUntilText(headingRecommendedItem);
    }

    public boolean isHeadingRecommendedItemsExist(){
        return isElementPresent(headingRecommendedItem);
    }

    // 点击推荐商品区域的第一个"Add to Cart"按钮
    public void clickFirstRecommendedProductAddToCart(){
        // 等待轮播图中的 active item 加载完成
        waitForElementVisible(firstRecommendedProduct);
        // 优先使用普通点击，如果失败则使用 JS 点击（轮播图可能有动画）
        clickElement(firstRecommendedAddToCartBtn);
    }

    // 可选：如果需要特定位置的推荐商品，可以指定索引
    public void clickRecommendedProductByIndex(int index){
        By productBtn = By.xpath(String.format("(//div[@class='item active']//a[@data-product-id])[%d]", index));
        waitForElementVisible(productBtn);
        clickElement(productBtn);
    }

    // 验证推荐商品区域可见
    public boolean isRecommendedItemsVisible(){
        return isElementPresent(recommendedItemsContainer);
    }

    // 滚动到页面底部
    public void scrollToBottom(){
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 滚动到页面顶部
    public void scrollToTop(){
        js.executeScript("window.scrollTo(0, 0)");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 点击右下角箭头按钮向上滚动
    public void clickScrollUpArrow(){
        By scrollUpArrow = By.id("scrollUp");
        waitForElementVisible(scrollUpArrow);
        clickElement(scrollUpArrow);
    }

    // 验证首页标题文本可见
    public boolean isHomePageTitleVisible(){
        return isElementPresent(HomePageTitle);
    }
}
