package tests;

import base.BaseTest;
import base.DriverManager;
import dataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class AppLaunchTest extends BaseTest {

    @Test(priority =1, enabled = true)
    public void verifyAppLaunch(){

        Assert.assertNotNull(
                DriverManager.getDriver(),
                "Driver is not Initialized"
        );

        System.out.println("Application launched Successfully");

    }

    @Test(priority =2, enabled = true)
    public void verifyStartupPopupIsDisplayed(){

        PopupPage popupPage = new PopupPage();

        Assert.assertTrue(popupPage.isPopupDisplayed(),"Startup Popup  is not Displayed");
        System.out.println("Startup Popup Displayed Successfully");

        popupPage.closepopup();

    }

    @Test(priority =3, enabled = true)
    public void verifyStartPopupTitle(){
        PopupPage popupPage =new PopupPage();

        Assert.assertEquals(popupPage.getPopupTitle(),"Android App Compatibility");
        popupPage.closepopup();
    }

    @Test(priority =4, enabled = true, dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void endToEndFlow(String username, String password) throws InterruptedException {

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        SideMenuPage sideMenuPage= new SideMenuPage();
        LoginPage loginPage= new LoginPage();
        ProductDetailPage productDetailPage= new ProductDetailPage();
        CartPage cartPage= new CartPage();
        CheckOutPage checkOutPage= new CheckOutPage();
        PaymentMethodPage paymentMethodPage=new PaymentMethodPage();
        FinalReviewPage finalReviewPage= new FinalReviewPage();


        popupPage.closepopup();//Skip Startup Alert
        homePage.clickOpenMenu();//Open side Menu
        sideMenuPage.clickLoginBtn();//Open Login Page
//        loginPage.login(username, password);
        loginPage.login(username, password);//Login
        homePage.clickProduct(); // Navigate to Product Page && Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product
        productDetailPage.clickCartBtn();//Open Cart Page
        cartPage.emptyCart(); // Remove Item from Cart
        cartPage.clickGoShopping();  //Go to Product listing Page from cart Page

        Thread.sleep(5000);
        homePage.clickProduct();//Again open Product Detail Page
        productDetailPage.clickAddToCartBtn(); //Add Product
        productDetailPage.clickCartBtn();// Go to Cart Page
        cartPage.clickProceedToCheckoutBtn();//Click proceed to checkout
        checkOutPage.enterDetails();//Enter shipping Details
        checkOutPage.submitForm(); // Submit the form
        Thread.sleep(5000);
        paymentMethodPage.enterPaymentDetails(); //Enter Payment Menthod
        paymentMethodPage.clickReviewOrderBtn();
        finalReviewPage.clickPlaceOrderBtn();//FinalReview place order page
    }
}

