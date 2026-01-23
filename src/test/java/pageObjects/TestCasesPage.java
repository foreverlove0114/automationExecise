package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestCasesPage extends BasePage{
    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//b[text()='Test Cases']")
    private WebElement headingTestCasesPage;

    public boolean isHeadingTestCasesPagePresent(){
        return isElementPresent(headingTestCasesPage);
    }
}
