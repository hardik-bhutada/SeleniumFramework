package org.pageobjects;


import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartItems;

    @FindBy(css = ".totalRow button")
    WebElement checkOutButton;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductDisplay(String productName){
        Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goToCheckoutPage(){
        checkOutButton.click();
        return new CheckoutPage(driver);
    }

}
