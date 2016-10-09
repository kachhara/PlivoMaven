package com.plivo.login;

import com.plivo.Common;
import com.plivo.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class Login {

    private RemoteWebDriver driver;
    private HashMap<String,String> map=new HashMap<String,String>();
    PropertyReader prop;
    Common com;
    public Login(RemoteWebDriver driver){
        this.driver=driver;
        prop = new PropertyReader();
        map=prop.getPropValues("login");
        com= new Common(this.driver);
        }

    public String loginTitle(){
        com.waitForPageToLoad("https://manage.plivo.com");
        return com.getTitle();

    }

    public boolean isLoginPage(){
     return com.isElementDisplayed(By.xpath(map.get("LOGIN_BUTTON")));

    }

    public void login(){
        com.waitForPresenceOfElement(By.xpath(map.get("USER_NAME")));
        com.click(By.xpath(map.get("USER_NAME")));
        com.input(By.xpath(map.get("USER_NAME")),map.get("USER"));
        com.click(By.xpath(map.get("USER_PASSWORD")));
        com.input(By.xpath(map.get("USER_PASSWORD")),map.get("PASSWORD"));
        com.click(By.xpath(map.get("LOGIN_BUTTON")));
    }



}
