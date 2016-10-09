package com.plivo.subaccount;

import com.plivo.Common;
import com.plivo.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class SubAccount {

    private RemoteWebDriver driver;
    private HashMap<String,String> map=new HashMap<String,String>();
    PropertyReader prop;
    Common com;
    public SubAccount(RemoteWebDriver driver){
        this.driver=driver;
        prop = new PropertyReader();
        map=prop.getPropValues("subaccount");
        com= new Common(this.driver);
    }


    public String subAccountTitle(){
        com.waitForPageToLoad("https://manage.plivo.com");
        return com.getTitle();

    }

    public void navigateToPaymentPage(){
        com.click(By.xpath(map.get("PAYMENT_LINK")));

    }

}
