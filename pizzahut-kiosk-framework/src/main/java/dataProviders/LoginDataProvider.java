package dataProviders;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() throws Exception {

        return ExcelUtils.getLoginData();
    }
}