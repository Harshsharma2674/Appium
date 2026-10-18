package pages;

import io.appium.java_client.AppiumBy;
import base.DriverManager;
import org.openqa.selenium.By;


public class PopupPage {

    private  final By popTitle = AppiumBy.id("android:id/alertTitle");
    private final By dontShowAgainBtn = AppiumBy.id("android:id/button1");


    public Boolean isPopupDisplayed(){

        return DriverManager.getDriver().findElement(popTitle).isDisplayed();

    }

    public String getPopupTitle(){
        return DriverManager.getDriver().findElement(popTitle).getText();
    }

    public void closepopup(){
        DriverManager.getDriver().findElement(dontShowAgainBtn).click();
    }
}
