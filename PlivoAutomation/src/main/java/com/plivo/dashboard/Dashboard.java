package com.plivo.dashboard;

import com.plivo.Common;
import com.plivo.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class Dashboard {
    private RemoteWebDriver driver;
    private HashMap<String,String> map=new HashMap<String,String>();
    PropertyReader prop;
    Common com;
    public Dashboard(RemoteWebDriver driver){
        this.driver=driver;
        prop = new PropertyReader();
        map=prop.getPropValues("dashboard");
        com= new Common(this.driver);
    }


    public String dashBoardTitle(){
        com.waitForPageToLoad("https://manage.plivo.com");
        return com.getTitle();

    }

    public String isDashBoardButtonSelected(){
        com.waitForPresenceOfElement(By.xpath(map.get("DASHBOARD_BUTTON")));
        return com.findElement(By.xpath(map.get("DASHBOARD_ACTIVE"))).getAttribute("class");

    }

    public void navigateToAccountPage(){
        com.click(By.xpath(map.get("ACCOUNT_LINK")));

    }
}
