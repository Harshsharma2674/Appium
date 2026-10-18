package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class FinalReviewPage {
    private final By placeOrderBtn = AppiumBy.xpath("//android.widget.TextView[@text='Place Order']");

    public void clickPlaceOrderBtn(){
        DriverManager.getDriver().findElement(placeOrderBtn).click();
    }
}