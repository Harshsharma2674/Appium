package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.sql.Driver;

public class CheckOutPage {
    private final By fullNameField= AppiumBy.accessibilityId("Full Name* input field");
    private final By addressField= AppiumBy.accessibilityId("Address Line 1* input field");
    private final By cityField = AppiumBy.accessibilityId("City* input field");
    private final By zipCodeField = AppiumBy.accessibilityId("Zip Code* input field");
    private final By countryField = AppiumBy.accessibilityId("Country* input field");
    private final By toPaymentBtn = AppiumBy.accessibilityId("To Payment button");


    public void enterDetails(){
        DriverManager.getDriver().findElement(fullNameField).sendKeys("Harsh");
        DriverManager.getDriver().findElement(addressField).sendKeys("Street");
        DriverManager.getDriver().findElement(cityField).sendKeys("Mohali");
        DriverManager.getDriver().findElement(zipCodeField).sendKeys("123456");
        DriverManager.getDriver().findElement(countryField).sendKeys("India");
    }

    public void submitForm(){
        DriverManager.getDriver().findElement(toPaymentBtn).click();
    }
}
