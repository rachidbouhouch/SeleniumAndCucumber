package org.sid.setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sid.utilities.ReadConfigProperties;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SetUp {
    protected static WebDriver driver;
    private static ReadConfigProperties readConfigProperties;

    private final String nameFile="config";
    public static WebDriver getSession() {
        return driver;
    }
    public static ReadConfigProperties getReadConfigProperties() {
        return readConfigProperties;
    }


    public void init() throws MalformedURLException {

        readConfigProperties=new ReadConfigProperties(nameFile);
        String browser = System.getProperty("browser",readConfigProperties.getPropertiesConfig().getProperty("browser"));

        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(readConfigProperties.getPropertiesConfig().getProperty("status.accessdenied"));
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }
        else if(browser.equalsIgnoreCase("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(readConfigProperties.getPropertiesConfig().getProperty("status.accessdenied"));
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().window().maximize();
    }

}