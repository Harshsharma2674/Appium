package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartPage {
    private final By removeItemBtn= AppiumBy.xpath("//*[@text='Remove Item']");
    private final By goShoppingBtn = AppiumBy.accessibilityId("Go Shopping button");
    private final By proceedToCheckoutBtn = AppiumBy.accessibilityId("Proceed To Checkout button");

    //Clearing Product from Cart
    public void emptyCart(){
        DriverManager.getDriver().findElement(removeItemBtn).click();
    }

    //Go to Product page
    public void clickGoShopping(){
        DriverManager.getDriver().findElement(goShoppingBtn).click();
    }

    //Click the proceed Button
    public void clickProceedToCheckoutBtn(){
        DriverManager.getDriver().findElement(proceedToCheckoutBtn).click();
    }
}
