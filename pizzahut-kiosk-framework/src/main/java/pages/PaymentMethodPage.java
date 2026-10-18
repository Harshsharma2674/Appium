package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.sql.Driver;

public class PaymentMethodPage {
    private final By fullNameField = AppiumBy.accessibilityId("Full Name* input field");
    private final By cardNumberField = AppiumBy.accessibilityId("Card Number* input field");
    private final By expiryDateField = AppiumBy.accessibilityId("Expiration Date* input field");
    private final By securityCodeField = AppiumBy.accessibilityId("Security Code* input field");
    private final By reviewOrderBtn = AppiumBy.xpath("//android.widget.TextView[@text='Review Order']");

    public void enterPaymentDetails() {

        DriverManager.getDriver().findElement(fullNameField).clear();
        DriverManager.getDriver().findElement(fullNameField).sendKeys("Harsh Sharma");

        DriverManager.getDriver().findElement(cardNumberField).clear();
        DriverManager.getDriver().findElement(cardNumberField).sendKeys("2223000000000007");

        DriverManager.getDriver().findElement(expiryDateField).clear();
        DriverManager.getDriver().findElement(expiryDateField).sendKeys("0329");

        DriverManager.getDriver().findElement(securityCodeField).clear();
        DriverManager.getDriver().findElement(securityCodeField).sendKeys("123");
    }

    public void clickReviewOrderBtn() {
        DriverManager.getDriver().findElement(reviewOrderBtn).click();
    }

}
