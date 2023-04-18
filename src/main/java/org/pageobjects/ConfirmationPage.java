package org.pageobjects;


import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(xpath = "//h1[normalize-space()='Thankyou for the order.']")
    WebElement confirmationMsg;

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String verifyConfirmationMsg(){
        return confirmationMsg.getText();
    }
}
