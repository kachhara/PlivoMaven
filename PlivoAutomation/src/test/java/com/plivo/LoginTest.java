package com.plivo.logintest;

import com.plivo.TestBase;
import com.plivo.dashboard.Dashboard;
import com.plivo.login.Login;
import com.plivo.payment.Payment;
import com.plivo.subaccount.SubAccount;
import com.sun.media.sound.InvalidDataException;
import org.omg.DynamicAny.DynAnyPackage.InvalidValue;
import org.openqa.selenium.InvalidElementStateException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.naming.directory.InvalidAttributeValueException;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class LoginTest extends TestBase {
    Login login = null;
    Dashboard db = null;
    SubAccount sa = null;
    Payment pay = null;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        setup();
        login = new Login(driver);
        db = new Dashboard(driver);
        sa = new SubAccount(driver);
        pay = new Payment(driver);
    }

    @Test
    public void couponRedeemAndValidation() {
        Assert.assertEquals(login.loginTitle(), "Plivo", "Login Title Mismatch");
        Assert.assertTrue(login.isLoginPage(), "Login Button did not dispaly");
        login.login();
        Assert.assertEquals(db.dashBoardTitle(), "Plivo | Dashboard", "DashBoard Title Mismatch");
        Assert.assertEquals(db.isDashBoardButtonSelected(), "active", "DashBoard Button is not selected by default");
        db.navigateToAccountPage();
        Assert.assertEquals(sa.subAccountTitle(), "Plivo | Sub Account", "SubAccount Title Mismatch");
        sa.navigateToPaymentPage();
        Assert.assertEquals(pay.paymentTitle(), "Plivo | Upgrade", "Payment Title Mismatch");
        pay.redeemCode();
        String couponVerificationMessage = pay.couponAuthentocation();
        //System.out.println("Coupon verification is : " + couponVerificationMessage);
        if (couponVerificationMessage.contains("invalid") || couponVerificationMessage.isEmpty())

            throw new IllegalArgumentException("What is Status of Entered Coupon:= " + couponVerificationMessage);

        else
            System.out.println("Coupon verification Status is : " + couponVerificationMessage);
    }

    @AfterMethod(alwaysRun = true)
    public void AfterMethod() {
        driver.close();
        driver.quit();
    }

}