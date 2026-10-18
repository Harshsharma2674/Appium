package tests;

import base.BaseTest;
import dataProviders.LoginDataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PopupPage;
import pages.SideMenuPage;
import utils.ExtentManager;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData",
    dataProviderClass = LoginDataProvider.class)
    public void loginTest(String username, String password){
        PopupPage popupPage = new PopupPage();
        HomePage homePage = new HomePage();
        SideMenuPage sideMenuPage = new SideMenuPage();
        LoginPage loginPage= new LoginPage();

        System.out.println("Username: "+ username);

        popupPage.closepopup();//Skip Startup Alert
        ExtentManager.getTest().info("Closed startup popup");

        homePage.clickOpenMenu();//Open side Menu
        sideMenuPage.clickLoginBtn();//Open Login Page
        ExtentManager.getTest().info("Opened Login page");

        loginPage.login(username, password);
        ExtentManager.getTest().info("Logged in as " + username);
    }


}
