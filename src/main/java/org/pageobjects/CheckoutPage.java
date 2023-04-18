package org.pageobjects;


import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement selectCountryInput;

    @FindBy(css = ".btnn.action__submit.ng-star-inserted")
    WebElement proceedBtn;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

    By results = By.cssSelector(".ta-results");

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName){
        Actions action = new Actions(driver);
        action.sendKeys(selectCountryInput,countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        //JavaScript Executor to click element
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", proceedBtn);
        return new ConfirmationPage(driver);
    }
}
