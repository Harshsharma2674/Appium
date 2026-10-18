package tests;

import dataProviders.LoginDataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest {

    @Test(dataProvider = "loginData",
    dataProviderClass = LoginDataProvider.class)
    public void loginTest(String username, String password){
        LoginPage loginPage= new LoginPage();
        System.out.println("Username: "+ username);
        System.out.println("Password: "+password);

        loginPage.login(username, password);
    }


}
