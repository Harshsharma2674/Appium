package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class HomePage {
    private final By menuBtn = AppiumBy.accessibilityId("open menu");// open menu
    private final By productItem = AppiumBy.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']");

    public void clickOpenMenu(){
        DriverManager.getDriver().findElement(menuBtn).click();
    }

    public void clickProduct(){
        DriverManager.getDriver().findElement(productItem).click();
    }
}
