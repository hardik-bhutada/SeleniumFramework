package org.hardikb.tests;

import org.hardikb.testcomponents.BaseTest;

import org.openqa.selenium.WebElement;
import org.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String , String> input) throws IOException {

        ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(input.get("productName"));
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage= cartPage.goToCheckoutPage();

        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String msg = confirmationPage.verifyConfirmationMsg();
        Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
    }

    // Dependency test - To verify Zara Coat 3 is displayed in orders page

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(){
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("seleniumframework@gmail.com", "Selenium@10");
        OrderPage orderPage = productCataloguePage.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplayed(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("email", "seleniumframework@gmail.com");
//        map.put("password","Selenium@10");
//        map.put("productName","ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("email", "seleniumframework@gmail.com");
//        map1.put("password","Selenium@10");
//        map1.put("productName","ADIDAS ORIGINAL");

        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\org\\hardikb\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}
