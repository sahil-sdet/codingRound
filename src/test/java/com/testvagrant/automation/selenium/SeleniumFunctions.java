package com.testvagrant.automation.selenium;

import com.testvagrant.automation.net.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * created by sahil.kashyap on 20/01/19
 */
public class SeleniumFunctions {

    final static Logger logger = Logger.getLogger(SeleniumFunctions.class);

    private static Map<String, String> executionContext = new HashMap<>();

    public static void EnterText(By elementBy, String text) {

        if (DriverManager.getDriver() != null) {
            try {
                DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(elementBy));
                if (!"".equals(DriverManager.getDriver().findElement(elementBy).getAttribute("value"))) {
                    DriverManager.getDriver().findElement(elementBy).clear();
                }
                if (!"".equals(text)) {
                    DriverManager.getDriver().findElement(elementBy).sendKeys(text);
                }
                logger.info("Text Entered for " + elementBy.toString() + " as :" + text);
            } catch (ElementNotVisibleException e) {
                logger.error("WebElement not found hence unable to enter text:" + text);
                throw (e);
            } catch (Exception e) {
                logger.error("Exception occurred while entering text:" + e.getMessage());
                e.printStackTrace();
                throw (e);
            }

        } else {
            logger.error("Driver is not initialized or browser is now closed.");
            throw new WebDriverException("Driver is not initialized or browser is now closed.");
        }
    }

    public static void Click(By elementBy) {
        if (DriverManager.getDriver() != null) {
            boolean elementFound = false;
            DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(elementBy));
            List<WebElement> elements = DriverManager.getDriver().findElements(elementBy);
            for (WebElement el : elements) {
                if (!elementFound) {
                    try {
                        DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(el));
                        new Actions(DriverManager.getDriver()).click(el).build().perform();
                        logger.info("Clicked on:" + el);
                        elementFound = true;
                        break;
                    } catch (Exception e) {
                        elementFound = false;
                    }
                }
            }
            if (!elementFound) {
                logger.error("WebElement not found hence unable to perform click operation for:" + elementBy.toString());
                throw new ElementNotVisibleException("WebElement not found hence unable to perform click operation: " + elementBy.toString());
            }
        } else {
            logger.error("Driver is not initialized or browser is now closed.");
            throw new WebDriverException("Driver is not initialized or browser is now closed.");
        }
    }

    public static void waitUntil(By elementBy) {
        WebDriverWait wait = DriverManager.getWait();
        if (DriverManager.getDriver() != null) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
        } else {
            logger.error("Driver is not initialized or browser is now closed.");
            throw new WebDriverException("Driver is not initialized or browser is now closed.");
        }
    }

    public static void waitUntilInvisibility(By elementBy) {
        WebDriverWait wait = DriverManager.getWait();
        if (DriverManager.getDriver() != null) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
        } else {
            logger.error("Driver is not initialized or browser is now closed.");
            throw new WebDriverException("Driver is not initialized or browser is now closed.");
        }
    }

    public static void SelectValue(By elementBy, String optionValue) {

        if (DriverManager.getDriver() != null) {
            try {
                DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(elementBy));
                Select dropdown = new Select(DriverManager.getDriver().findElement(elementBy));
                dropdown.selectByVisibleText(optionValue);
                logger.info("Value selected from dropdown for " + elementBy.toString() + " as :" + optionValue);
            } catch (ElementNotVisibleException e) {
                logger.error("WebElement(dropdown) not found hence unable to select value:" + optionValue);
                throw (e);
            } catch (Exception e) {
                logger.error("Exception occurred while selecting value from dropdown:" + e.getMessage());
                e.printStackTrace();
                throw (e);
            }

        } else {
            logger.error("Driver is not initialized or browser is now closed.");
            throw new WebDriverException("Driver is not initialized or browser is now closed.");
        }}

        public static void Submit(){
        DriverManager.getDriver().findElement(By.className("submit")).submit();
        }

        public static void threadSleep(int waitMillis){
            try {
                Thread.sleep(waitMillis);
            } catch (InterruptedException e) {
                logger.info("Error on thread sleeping.");
            }
        }
    }
