package com.plivo;

import com.plivo.utils.InitiateDriver;
import com.plivo.utils.PropertyReader;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.HashMap;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class TestBase {
    protected RemoteWebDriver driver;
    String env=null;
    String file= "configuration.properties";
    PropertyReader prop=new PropertyReader();
    InitiateDriver initiateDriver=new InitiateDriver();
    HashMap<String,String> map=new HashMap<String,String>();
    public void setup()
    {
        map=prop.getPropValues(file);
        driver = initiateDriver.getDriver();

        env=map.get("ENV");

        try {
            if (env.equalsIgnoreCase("prod")) {
                driver.get("https://manage.plivo.com");
                driver.manage().window().maximize();
            } else {
                driver.get("https://www.plivo.com");
            }
        } catch (Exception e) {
            driver.get("https://www.plivo.com/");
        }


    }


    }


