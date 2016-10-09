package com.plivo.utils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class InitiateDriver {
    private RemoteWebDriver driver;

    public static final String file= "configuration.properties";
    public InitiateDriver() {
        try {
            PropertyReader prop = new PropertyReader();
            String platform = prop.getPropValues(file).get("PLATFORM");
            String runOn = prop.getPropValues(file).get("RUN_ON");
            String browser = prop.getPropValues(file).get("BROWSER");
            String url = prop.getPropValues(file).get("URL");

            if (platform.equalsIgnoreCase("WINDOWS")) {
                if (runOn.equalsIgnoreCase("WEBSITE")) {
                    driver = new RemoteWebDriver(new URL(url), getBrowserCapabilities(browser, runOn));
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public RemoteWebDriver getDriver() {
        if (driver == null)
            throw new RuntimeException("We have not instantiated the driver.");
        return driver;
    }


    private DesiredCapabilities getBrowserCapabilities(String browser, String runOn) {
        DesiredCapabilities capabilities = null;
        try {
            if (runOn.equalsIgnoreCase("WEBSITE") && browser.equalsIgnoreCase("Firefox")) {
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setPlatform(Platform.ANY);
            } else if (runOn.equalsIgnoreCase("WEBSITE") && browser.equalsIgnoreCase("Chrome")) {

                System.setProperty("webdriver.chrome.driver", "C:\\Users\\" + System.getProperty("user.name") + "/Documents/chromedriver");

                capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setPlatform(Platform.ANY);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return capabilities;
    }
}