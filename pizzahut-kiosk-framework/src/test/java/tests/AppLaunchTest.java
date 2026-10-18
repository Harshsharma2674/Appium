package tests;

import base.BaseTest;
import base.DriverManager;
import dataProviders.LoginDataProvider;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AppLaunchTest extends BaseTest {

    //BaseTest starts a fresh app before EVERY test, so each test repeats the steps it needs from the start.

    //Elements used only for checking (assertions) in this class
    private final By productsTitle = AppiumBy.xpath("//android.widget.TextView[@text='Products']");
    private final By addToCartBtn = AppiumBy.xpath("//android.widget.TextView[@text='Add To Cart']");
    private final By cartBadgeCount = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.TextView");
    private final By proceedToCheckoutBtn = AppiumBy.accessibilityId("Proceed To Checkout button");
    private final By noItemsText = AppiumBy.xpath("//android.widget.TextView[@text='No Items']");
    private final By shippingAddressTitle = AppiumBy.xpath("//android.widget.TextView[@text='Enter a shipping address']");
    private final By paymentMethodTitle = AppiumBy.xpath("//android.widget.TextView[@text='Enter a payment method']");
    private final By reviewOrderTitle = AppiumBy.xpath("//android.widget.TextView[@text='Review your order']");
    private final By checkoutCompleteText = AppiumBy.xpath("//android.widget.TextView[@text='Checkout Complete']");

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

    @Test(priority =4, description = "User should land on the Products page after login",
            dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void verifyLogin(String username, String password){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        SideMenuPage sideMenuPage= new SideMenuPage();
        LoginPage loginPage= new LoginPage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickOpenMenu();//Open side Menu
        sideMenuPage.clickLoginBtn();//Open Login Page
        loginPage.login(username, password);//Login

        Assert.assertTrue(DriverManager.getDriver().findElement(productsTitle).isDisplayed(),
                "Products page is not displayed after login with " + username);
    }

    @Test(priority =5, description = "Clicking a product should open the Product Details page")
    public void verifyProductDetailsPage(){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickProduct(); //Open Product Details Page

        Assert.assertTrue(DriverManager.getDriver().findElement(addToCartBtn).isDisplayed(),
                "Product Details page is not displayed");
    }

    @Test(priority =6, description = "Adding a product should show 1 on the cart icon")
    public void verifyAddToCart(){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        ProductDetailPage productDetailPage= new ProductDetailPage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickProduct(); //Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product

        Assert.assertEquals(DriverManager.getDriver().findElement(cartBadgeCount).getText(), "1",
                "Cart count is wrong after adding a product");
    }

    @Test(priority =7, description = "Removing the product should leave the cart empty")
    public void verifyRemoveItemFromCart(){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        ProductDetailPage productDetailPage= new ProductDetailPage();
        CartPage cartPage= new CartPage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickProduct(); //Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product
        productDetailPage.clickCartBtn();//Open Cart Page

        //Proceed To Checkout is only shown when the cart has items
        Assert.assertTrue(DriverManager.getDriver().findElement(proceedToCheckoutBtn).isDisplayed(),
                "Product is not in the cart");

        cartPage.emptyCart(); // Remove Item from Cart

        Assert.assertTrue(DriverManager.getDriver().findElement(noItemsText).isDisplayed(),
                "Cart is not empty after removing the product");
    }

    @Test(priority =8, description = "Go Shopping on an empty cart should open the Products page")
    public void verifyGoShoppingFromEmptyCart(){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        ProductDetailPage productDetailPage= new ProductDetailPage();
        CartPage cartPage= new CartPage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickProduct(); //Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product
        productDetailPage.clickCartBtn();//Open Cart Page
        cartPage.emptyCart(); // Remove Item from Cart
        cartPage.clickGoShopping();  //Go to Product listing Page from cart Page

        Assert.assertTrue(DriverManager.getDriver().findElement(productsTitle).isDisplayed(),
                "Products page is not displayed after clicking Go Shopping");
    }

    @Test(priority =9, description = "Proceed To Checkout should open the shipping address page",
            dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void verifyCheckoutPage(String username, String password){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        SideMenuPage sideMenuPage= new SideMenuPage();
        LoginPage loginPage= new LoginPage();
        ProductDetailPage productDetailPage= new ProductDetailPage();
        CartPage cartPage= new CartPage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickOpenMenu();//Open side Menu
        sideMenuPage.clickLoginBtn();//Open Login Page
        loginPage.login(username, password);//Login
        homePage.clickProduct(); //Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product
        productDetailPage.clickCartBtn();// Go to Cart Page
        cartPage.clickProceedToCheckoutBtn();//Click proceed to checkout

        Assert.assertTrue(DriverManager.getDriver().findElement(shippingAddressTitle).isDisplayed(),
                "Checkout (shipping address) page is not displayed");
    }

    @Test(priority =10, description = "Submitting the shipping address should open the payment page",
            dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void verifyPaymentPage(String username, String password){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        SideMenuPage sideMenuPage= new SideMenuPage();
        LoginPage loginPage= new LoginPage();
        ProductDetailPage productDetailPage= new ProductDetailPage();
        CartPage cartPage= new CartPage();
        CheckOutPage checkOutPage= new CheckOutPage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickOpenMenu();//Open side Menu
        sideMenuPage.clickLoginBtn();//Open Login Page
        loginPage.login(username, password);//Login
        homePage.clickProduct(); //Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product
        productDetailPage.clickCartBtn();// Go to Cart Page
        cartPage.clickProceedToCheckoutBtn();//Click proceed to checkout
        checkOutPage.enterDetails();//Enter shipping Details
        checkOutPage.submitForm(); // Submit the form

        Assert.assertTrue(DriverManager.getDriver().findElement(paymentMethodTitle).isDisplayed(),
                "Payment page is not displayed");
    }

    @Test(priority =11, description = "Submitting payment details should open the Review Order page",
            dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void verifyReviewOrderPage(String username, String password){

        PopupPage popupPage=new PopupPage();
        HomePage homePage = new HomePage();
        SideMenuPage sideMenuPage= new SideMenuPage();
        LoginPage loginPage= new LoginPage();
        ProductDetailPage productDetailPage= new ProductDetailPage();
        CartPage cartPage= new CartPage();
        CheckOutPage checkOutPage= new CheckOutPage();
        PaymentMethodPage paymentMethodPage=new PaymentMethodPage();

        popupPage.closepopup();//Skip Startup Alert
        homePage.clickOpenMenu();//Open side Menu
        sideMenuPage.clickLoginBtn();//Open Login Page
        loginPage.login(username, password);//Login
        homePage.clickProduct(); //Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product
        productDetailPage.clickCartBtn();// Go to Cart Page
        cartPage.clickProceedToCheckoutBtn();//Click proceed to checkout
        checkOutPage.enterDetails();//Enter shipping Details
        checkOutPage.submitForm(); // Submit the form
        paymentMethodPage.enterPaymentDetails(); //Enter Payment Method
        paymentMethodPage.clickReviewOrderBtn();

        Assert.assertTrue(DriverManager.getDriver().findElement(reviewOrderTitle).isDisplayed(),
                "Review Order page is not displayed");
    }

    @Test(priority =12, description = "Placing the order should show Checkout Complete",
            dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void verifyPlaceOrder(String username, String password){

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
        loginPage.login(username, password);//Login
        homePage.clickProduct(); //Open Product Details Page
        productDetailPage.clickAddToCartBtn(); //Add product
        productDetailPage.clickCartBtn();// Go to Cart Page
        cartPage.clickProceedToCheckoutBtn();//Click proceed to checkout
        checkOutPage.enterDetails();//Enter shipping Details
        checkOutPage.submitForm(); // Submit the form
        paymentMethodPage.enterPaymentDetails(); //Enter Payment Method
        paymentMethodPage.clickReviewOrderBtn();
        finalReviewPage.clickPlaceOrderBtn();//FinalReview place order page

        Assert.assertTrue(DriverManager.getDriver().findElement(checkoutCompleteText).isDisplayed(),
                "Order was not placed");
    }
}
