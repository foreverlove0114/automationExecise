package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    protected void waitForElementVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

//    protected void clickElement(WebElement element){
//        waitForElementClickable(element);
//        element.click();
//    }

    protected void clickElementJS(WebElement element) {
        waitForElementVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected void sendKeysToElement(WebElement element, String text){
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isElementPresent(WebElement element){
        try{
            waitForElementVisible(element);
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    protected void selectByVisibleText(WebElement element,String text){
        waitForElementVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void handleAlertWithClickOK(){
        /*
        alert.accept()	点击“确定”或“OK”	处理你的 OK 按钮
        alert.dismiss()	点击“取消”或“关闭”	处理你的 Cancel 按钮
        alert.getText()	获取弹窗上的文字内容	验证是否出现了正确的提示
        alert.sendKeys("text")	在弹窗输入框中输入文字	仅适用于 prompt() 类型的弹窗
        * */

        // 1. 使用显式等待，确保弹窗已经跳出来
        wait.until(ExpectedConditions.alertIsPresent());

        // 2. 切换到弹窗
        Alert alert = driver.switchTo().alert();

        // 3. 读取弹窗文本 (例如: "Press OK to proceed!")
        String alertText = alert.getText();
        System.out.println("Content of Alert: " + alertText);

        // 4. 点击 OK (Accept) 或者 Cancel (Dismiss)
        alert.accept();// 相当于点击 OK
    }

    public void scrollDownUntilText(WebElement element){
        waitForElementVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()",element);
    }
}
