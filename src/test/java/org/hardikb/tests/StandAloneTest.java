package org.hardikb.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.LandingPage;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();

        String productName = "ZARA COAT 3";
        LandingPage landingPage = new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("seleniumframework@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Selenium@10");
        driver.findElement(By.id("login")).click();

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

        driver.findElement(By.xpath("//button[@routerLink='/dashboard/cart']")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

        Assert.assertTrue(cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName)));

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".action__submit"))));
        WebElement proceedBtn = driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));

        //JavaScript Executor to click element
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", proceedBtn);

        Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Thankyou for the order.']")).isDisplayed());

        driver.quit();
    }
}
