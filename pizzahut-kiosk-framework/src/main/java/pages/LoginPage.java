package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.sql.Driver;

public class LoginPage {
    private final By userNameField = AppiumBy.accessibilityId("Username input field");// Username Field
    private final By passwordField = AppiumBy.accessibilityId("Password input field");
    private  final By loginBtn = AppiumBy.accessibilityId("Login button");

    public void  login(String userName, String password){
        DriverManager.getDriver().findElement(userNameField).sendKeys(userName);
        DriverManager.getDriver().findElement(passwordField).sendKeys(password);
        DriverManager.getDriver().findElement(loginBtn).click();

    }

//    public void login(){
//        DriverManager.getDriver().findElement(userNameField).sendKeys("bob@example.com");
//        DriverManager.getDriver().findElement(passwordField).sendKeys("10203040");
//        DriverManager.getDriver().findElement(loginBtn).click();
//    }

}
