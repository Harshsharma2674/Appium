package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static AndroidDriver driver; //A framework should have only ONE place that creates drivers.
    //Private --> Only DriverManager can Modify it.
    //Static --> Only One copy Exists.

    public static AndroidDriver initializeDriver() throws Exception {

        UiAutomator2Options options =
                new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554");
        options.setUdid("emulator-5554");

        options.setApp(
                "C:\\Users\\hssharma\\Downloads\\Android-MyDemoAppRN.1.3.0.build-244.apk"
        );

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), //Java cannot directly understand a URL string
                // So Using new URL().
                options
        );

        //Implicit Wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
