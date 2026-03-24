package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage{
    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    // 统一使用 By 定位器
    private final By headingTestCasesPage = By.xpath("//b[text()='Test Cases']");

    public boolean isHeadingTestCasesPagePresent(){
        return isElementPresent(headingTestCasesPage);
    }
}
