package base;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static base.DriverManager.initializeDriver;

public class BaseTest {

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("Initializing Driver....");

        DriverManager.initializeDriver();
//        DriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button1']")).click();
    }

    @AfterMethod
    public void tearDown(){
//        System.out.println("Closing Driver.....");

//        DriverManager.quitDriver();

    }
}
