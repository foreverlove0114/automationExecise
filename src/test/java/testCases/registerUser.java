package testCases;

import org.testng.annotations.Test;

public class registerUser extends testBase.BaseClass{

    @Test
    public void register_user(){
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
    }
}
