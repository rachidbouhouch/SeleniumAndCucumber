package org.sid.stepsdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.sid.models.ProductItem;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sid.pages.CapsulePage;
import org.sid.setup.SetUp;
import org.sid.utilities.CommanStep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderStep {

    private WebDriver driver;

    private CapsulePage capsulePage;

    protected static final SetUp setUp=new SetUp();

    private Scenario scenario;


    @Before
    public void init(Scenario scenario) throws IOException {
        setUp.init();
        driver= SetUp.getSession();
        capsulePage=new CapsulePage(driver);
        this.scenario=scenario;
    }
    @Given("User opens the url {string}")
    public void userOpensTheUrl(String url) {
        driver.get(url);
        capsulePage.acceptCookies();
        System.out.println("url Opened");
        CommanStep.takeScreenCapture(scenario,driver);
    }

    @When("User scrolls to {} capsule")
    public void userScrollsToCapsule(String productName){
        capsulePage.ScrollToCapsule(productName);
        CommanStep.takeScreenCapture(scenario,driver);
    }

    @And("User adds {} units of {} capsule to cart")
    public void userAddsUnitsOfCapsuleToCart(String quantity, String productName) throws InterruptedException {
        capsulePage.SpecifyQuantityOfCapsule(quantity,productName);
        capsulePage.AddCapsuleToCart();
        CommanStep.takeScreenCapture(scenario,driver);
    }

    @Then("Mini cart contains {} units of {} capsule")
    public void miniCartContainsUnitsOfCapsule(String quantity, String productName) throws InterruptedException {
        List<Integer> mylist=new ArrayList();

        capsulePage.clickAddToCart();
        String result=capsulePage.quantityCapsuleFromCart(productName);
        System.out.println(result);
        //Check product name
        Assert.assertEquals(quantity,result);
        CommanStep.takeScreenCapture(scenario,driver);
    }

    @After
    public void close(){
        CommanStep.takeScreenCapture(scenario,driver);
        driver.quit();
    }
}
