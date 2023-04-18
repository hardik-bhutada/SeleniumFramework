package org.abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.CartPage;
import org.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//button[@routerLink='/dashboard/cart']")
    WebElement cartLink;

    @FindBy(xpath = "//button[@routerLink='/dashboard/myorders']")
    WebElement orderLink;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy){
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToAppear(WebElement findWebElement){
        wait.until(ExpectedConditions.visibilityOf(findWebElement));
    }

    public void waitForElementToDisappear(By findBy){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public CartPage goToCartPage(){
        cartLink.click();
        return new CartPage(driver);
    }

    public OrderPage goToOrdersPage(){
        orderLink.click();
        return new OrderPage(driver);
    }
}
