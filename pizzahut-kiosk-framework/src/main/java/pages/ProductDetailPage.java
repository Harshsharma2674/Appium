package pages;

import base.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.sql.Driver;

public class ProductDetailPage {
    private final By addToCartBtn = AppiumBy.xpath("//android.widget.TextView[@text='Add To Cart']");
    private final By cartBtn = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.ImageView");

    public void clickAddToCartBtn() {
        DriverManager.getDriver().findElement(addToCartBtn).click();
    }

    public void clickCartBtn(){
        DriverManager.getDriver().findElement(cartBtn).click();
    }



}
