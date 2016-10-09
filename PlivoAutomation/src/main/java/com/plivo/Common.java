package com.plivo;
import com.plivo.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

/**
 * Created by Rajat.Kachhara on 07-10-2016.
 */
public class Common{

    public static int DEFAULT_TIMEOUT = 60;
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    public Common(RemoteWebDriver driver){
        this.driver=driver;
    }

    /**
     * This method is used for waiting till the element is Present or Found using Webdriver
     *         wait
     *
     */

    public boolean  waitForPresenceOfElement(By locator) {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions
                    .presenceOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            nosuchElementException(locator.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * This method is used for waiting till the element is Visible using Webdriver
     *         wait
     *
          */
    public  boolean waitForVisibilityOfElement(By locator) {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions
                    .visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            nosuchElementException(locator.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * This method is used for input in text field
     *
     */

    public  void input(By locator,String input) {
         try {
            waitForPresenceOfElement(locator);
            driver.findElement(locator).sendKeys(input);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            nosuchElementException(locator.toString());
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


    public  void nosuchElementException(String locator) {
        Assert.fail("Element [" + locator + "] is not found at url : " + driver.getCurrentUrl());
    }

    /**
     * This method is used for waiting till the element displayed on page
     *
     */
    public  boolean isElementDisplayed(By locator) {
        try {
            driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException n) {
            nosuchElementException(locator.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * This method is used for clicking on web element
     *
     */
    public  void click(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (NoSuchElementException e) {
            nosuchElementException("" + locator);
        }
    }

    /**
     * This method is used for getting web page's title
     *
     */
        public String getTitle(){
        return driver.getTitle();
        }


    public Actions actionObject(){
        Actions action=new Actions(driver);
        return action;
    }

    /**
     * This method is used for waiting till page is loaded
     *
     */
    public boolean waitForPageToLoad(final String pageURL) {
         try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            return wait.until(new ExpectedCondition<Boolean>() {
                                  public Boolean apply(WebDriver d) {
                                  return (driver.getCurrentUrl().toLowerCase().contains(pageURL.toLowerCase())) && driver.executeScript("return document.readyState;").equals("complete");
                                  }
                              }
            );
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public String doJavascriptOnElement(String javascript) {
       return ((JavascriptExecutor) driver).executeScript(javascript).toString();

    }

    public void waitForElement(long a){
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
