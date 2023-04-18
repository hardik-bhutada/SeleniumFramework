package org.hardikb.tests;

import org.hardikb.testcomponents.BaseTest;
import org.hardikb.testcomponents.Retry;
import org.openqa.selenium.WebElement;
import org.pageobjects.CartPage;
import org.pageobjects.ProductCataloguePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void verifyLoginError() throws IOException {
        String productName = "ZARA COAT 3";

        ProductCataloguePage productCataloguePage = landingPage.loginApplication("seleniumframek@gmail.com", "Selenium@10");
        Assert.assertEquals(landingPage.getErrorMessageText(), "Incorrect email or password.");
    }

    @Test
    public void productErrorValidations() throws IOException {
        String productName = "ZARA COAT 3";

        ProductCataloguePage productCataloguePage = landingPage.loginApplication("seleniumframework@gmail.com", "Selenium@10");

        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay("Zara Coat 33");
        Assert.assertFalse(match);
    }
}
