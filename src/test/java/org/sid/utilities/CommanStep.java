package org.sid.utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommanStep {

    public static void takeScreenCapture(Scenario scenario, WebDriver driver){
        if(scenario.isFailed()){
            TakesScreenshot ts=(TakesScreenshot) driver;
            byte[] screenshots = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshots,"image/png","taking screenshot for the scenario...");
        }
        else {
            TakesScreenshot ts=(TakesScreenshot) driver;
            byte[] screenshots = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshots,"image/png","taking attached for the scenario...");
        }


    }
}
