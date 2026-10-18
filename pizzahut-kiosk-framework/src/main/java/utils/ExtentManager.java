package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {

    private static ExtentReports extent; //One report for the whole run
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>(); //Test that is running right now

    public static synchronized ExtentReports getInstance() {

        if (extent == null) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String reportPath = Paths.get(System.getProperty("user.dir"), "reports",
                    "ExtentReport_" + timeStamp + ".html").toString();

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("PizzaHut Kiosk Automation Report");
            spark.config().setReportName("Appium Test Results");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Platform", "Android");
            extent.setSystemInfo("Device", ConfigReader.get("device.name"));
            extent.setSystemInfo("Tester", System.getProperty("user.name"));

            System.out.println("Extent report: " + reportPath);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        currentTest.set(test);
        return test;
    }

    //Use this from tests/pages to log steps: ExtentManager.getTest().info("Clicked login");
    public static ExtentTest getTest() {
        return currentTest.get();
    }

    public static void removeTest() {
        currentTest.remove();
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush(); //Writes the HTML file
        }
    }
}
