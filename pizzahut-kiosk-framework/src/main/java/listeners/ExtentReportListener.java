package listeners;

import base.DriverManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

public class ExtentReportListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.createTest(buildTestName(result), result.getMethod().getDescription());
        test.assignCategory(result.getTestClass().getRealClass().getSimpleName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getOrCreateTest(result).pass("Test Passed");
        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getOrCreateTest(result);
        test.fail(result.getThrowable());
        attachScreenshot(test);
        ExtentManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getOrCreateTest(result).skip(result.getThrowable() != null
                ? result.getThrowable().getMessage() : "Test Skipped");
        ExtentManager.removeTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush(); //Writes the HTML file
    }

    //A test skipped because @BeforeMethod failed never reaches onTestStart
    private ExtentTest getOrCreateTest(ITestResult result) {
        ExtentTest test = ExtentManager.getTest();
        return (test != null) ? test : ExtentManager.createTest(buildTestName(result), "");
    }

    private void attachScreenshot(ExtentTest test) {

        if (DriverManager.getDriver() == null) {
            return;
        }
        try {
            String base64 = DriverManager.getDriver().getScreenshotAs(OutputType.BASE64);
            test.fail("Screenshot on failure",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        } catch (Exception e) {
            test.warning("Could not capture screenshot: " + e.getMessage());
        }
    }

    //e.g. "loginTest [bob@example.com]" --> only the first value, so passwords are not printed in the report
    private String buildTestName(ITestResult result) {

        Object[] params = result.getParameters();
        String name = result.getMethod().getMethodName();
        return (params == null || params.length == 0) ? name : name + " [" + params[0] + "]";
    }
}
