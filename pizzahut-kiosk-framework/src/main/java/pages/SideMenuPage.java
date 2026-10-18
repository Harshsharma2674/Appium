package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class SideMenuPage {
    private final By catalogBtn= AppiumBy.xpath("//android.view.ViewGroup[@content-desc='menu item catalog']");// Catalog Button
    private final By loginBtn = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='menu item log in']");// login Button

    public void clickCatalogBtn(){
        DriverManager.getDriver().findElement(catalogBtn).click();
    }

    public void clickLoginBtn(){
        DriverManager.getDriver().findElement(loginBtn).click();
    }



}
