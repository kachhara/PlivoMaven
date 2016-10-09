package com.plivo.payment;

import com.plivo.Common;
import com.plivo.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class Payment {

    private RemoteWebDriver driver;
    private HashMap<String,String> map=new HashMap<String,String>();
    PropertyReader prop;
    Common com;
    public Payment(RemoteWebDriver driver){
        this.driver=driver;
        prop = new PropertyReader();
        map=prop.getPropValues("payment");
        com= new Common(this.driver);
    }


    public String paymentTitle(){
        com.waitForPageToLoad("https://manage.plivo.com");
        return com.getTitle();

    }

    public void redeemCode(){
        com.waitForPresenceOfElement(By.xpath(map.get("REDEEM_CODE_TEXT")));
        com.click(By.xpath(map.get("REDEEM_CODE_TEXT")));
        com.waitForPresenceOfElement(By.xpath(map.get("COUPON_POPUP")));
        com.actionObject().moveToElement(com.findElement(By.xpath(map.get("ENTER_COUPON_CODE"))));
        com.click(By.xpath(map.get("ENTER_COUPON_CODE")));
        com.input(By.xpath(map.get("ENTER_COUPON_CODE")),map.get("COUPON_CODE"));
        com.click(By.xpath(map.get("SUBMIT_COUPON")));
        }

     public String couponAuthentocation() {
         com.waitForPresenceOfElement(By.xpath(map.get("COUPON_AUTH")));
         com.actionObject().moveToElement(com.findElement(By.xpath(map.get("COUPON_AUTH"))));
         com.waitForElement(2000);
         return com.doJavascriptOnElement("return jQuery('#coupon_error').text()");

     }
}
