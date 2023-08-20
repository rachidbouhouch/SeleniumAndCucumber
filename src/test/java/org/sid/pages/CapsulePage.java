package org.sid.pages;

import org.sid.models.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CapsulePage {
    public WebDriver driver;
    @FindBy(xpath = "//button[@class='qty-selector-btn']")
    WebElement button_ok;
    @FindBy(id ="_evidon-accept-button")
    WebElement buttonAcceptCookies;
    @FindBy(xpath = "//a[@class='action showcart']")
    WebElement buttonShowCart;



    public CapsulePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void acceptCookies(){
        buttonAcceptCookies.click();
    }

    public void ScrollToCapsule(String capsuleName){
        WebElement capsuleToScrollTo = waitForElementPresenceOfElementLocated("//a[contains(@class,'product-item-link') and contains("+translate()+",'"+capsuleName+"')]/ancestor::div[contains(@class,'product-item-details')]//button[contains(@class,'action tocart')]");
        scrollIntoViewJSExecutor(capsuleToScrollTo,driver);
        moveToElementAndClick(driver,capsuleToScrollTo);
    }

    public void SpecifyQuantityOfCapsule(String quantity,String capsuleName) throws InterruptedException {
        WebElement productToScrollTo = waitForElementPresenceOfElementLocated("//a[contains(@class,'product-item-link') and contains("+translate()+",'"+capsuleName+"')]/ancestor::div[contains(@class,'product-item-details')]//input[contains(@class,'qty-selector-input')]");
        productToScrollTo.sendKeys(quantity);
    }

    public void AddCapsuleToCart(){
        waitForElementToBeClickable(button_ok,driver).click();
    }

    public void clickAddToCart() {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        moveToElementAndClick(driver,buttonShowCart);
    }

    public String quantityCapsuleFromCart(String capsuleName) {
        WebElement inputQuantity = waitForElementPresenceOfElementLocated("//a[contains("+translate()+",'"+capsuleName+"')]/ancestor::div//input[contains(@class,'cart-item-qty')]");
        return inputQuantity.getAttribute("value");
    }

    public String translate(){
        return "translate(text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')";
    }

    public void moveToElementAndClick(WebDriver driver,WebElement element){
        Actions action=new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }
    public WebElement waitForElementToBeClickable(WebElement element,WebDriver driver){
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5000))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void scrollIntoViewJSExecutor(WebElement elementToScrollTo,WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(false);", elementToScrollTo);
    }
    public WebElement waitForElementPresenceOfElementLocated(String elementText) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementText)));
        return driver.findElement(By.xpath(elementText));
    }
}
