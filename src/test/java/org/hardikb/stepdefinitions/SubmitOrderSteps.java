package org.hardikb.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hardikb.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.pageobjects.*;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class SubmitOrderSteps extends BaseTest {

    public LandingPage landingPage;
    public ProductCataloguePage productCataloguePage;

    public ConfirmationPage confirmationPage;

    public CheckoutPage checkoutPage;

    public CartPage cartPage;

    @Given("I landed on Ecommerce page")
    public void I_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^User is logged in with username (.+) and password (.+)$")
    public void userIsLoggedInWithUsernameNameAndPasswordPassword(String username, String password) {
        productCataloguePage = landingPage.loginApplication(username, password);

    }

    @When("^User adds the (.+) to cart$")
    public void userAddsTheProductToCart(String product) {
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(product);
    }

    @And("^User checkout (.+) and submit to order$")
    public void userCheckoutProductAndSubmitToOrder(String product) {
        cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(product);
        Assert.assertTrue(match);
        checkoutPage= cartPage.goToCheckoutPage();

        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("User verify {string} message is displayed on Confirmation page")
    public void userVerifyMessageIsDisplayedOnConfirmationPage(String string) {
        String msg = confirmationPage.verifyConfirmationMsg();
        Assert.assertTrue(msg.equalsIgnoreCase(string));
        driver.close();
    }
}
