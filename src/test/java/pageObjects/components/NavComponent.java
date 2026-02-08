package pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.*;

public class NavComponent {
    private WebDriver driver;

    public NavComponent(WebDriver driver) {
        this.driver = driver;
    }

    // 定位器统一使用 By，方便动态处理
    private By linkHome = By.xpath("//a[contains(text(),'Home')]");
    private By linkProducts = By.xpath("//a[@href='/products']");
    private By linkCart = By.xpath("//a[normalize-space()='Cart']");
    private By linkSignupLogin = By.xpath("//a[normalize-space()='Signup / Login']");
    private By linkDeleteAccount = By.xpath("//a[normalize-space()='Delete Account']");
    private By linkLogout = By.xpath("//a[normalize-space()='Logout']");
    private By linkUserStatus = By.partialLinkText("Logged in as");

    public RegisterLoginPage clickLogin() {
        driver.findElement(linkSignupLogin).click();
        return new RegisterLoginPage(driver);
    }

    public CartPage clickCart() {
        driver.findElement(linkCart).click();
        return new CartPage(driver);
    }

    public ProductsPage clickProducts() {
        driver.findElement(linkProducts).click();
        return new ProductsPage(driver);
    }

    public boolean isLoggedInAs(String username) {
        try {
            return driver.findElement(linkUserStatus).getText().contains(username);
        } catch (Exception e) { return false; }
    }

    public AccountDeletedPage clickDeleteAccount() {
        driver.findElement(linkDeleteAccount).click();
        return new AccountDeletedPage(driver);
    }
}