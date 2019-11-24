package com.vezeeta.automation.net;

import com.vezeeta.automation.utils.ChromeUtils;
import com.vezeeta.automation.utils.FirefoxUtils;
import com.vezeeta.automation.utils.PropertyUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import java.util.LinkedHashSet;
import java.util.Set;

public class DriverManager {

    private static final Logger logger = Logger.getLogger(DriverManager.class);
    private static WebDriver driver;
    private static ITestContext testContext;
    private static Set<String> windowHandles;
    private static WebDriverWait wait;
    private static Alert alert;
    public static boolean PROPERTIES_INITIALIZED = false;

    private DriverManager() {
        throw new IllegalStateException("Utility class");
    }

    public static void beforeSuite(ITestContext testContext){
        DriverManager.testContext = testContext;
        if (!PROPERTIES_INITIALIZED) {
            logger.info("Properties not initialized. Initializing");
            String countryConfig = System.getProperty("Country") == null ? "india" : System.getProperty("Country");
            if (null != countryConfig) {
                logger.info("Setting configuration:" + countryConfig);
                PropertyUtil.loadPropertyFile(countryConfig);
                PROPERTIES_INITIALIZED = true;
            } else {
                logger.error("Please specify country config property file name");
                throw new WebDriverException("Country config not provided.");
            }
        }
    }

    private static ChromeDriver createChromeDriver() {
        logger.info("ChromeDriver...");
        ChromeOptions options = ChromeUtils.getChromeOptions();
        try {
            System.setProperty("webdriver.chrome.driver"
                    , System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver_mac");
            return new ChromeDriver(options);
        } catch (IllegalStateException e) {
            logger.warn(e.getMessage());
            logger.info("Using Chrome driver for Mac");
            System.setProperty("webdriver.chrome.driver"
                    , System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver_mac");
            return new ChromeDriver(options);
        }
    }

    private static FirefoxDriver createFirefoxDriver() {
        logger.info("FirefoxDriver...");
        FirefoxOptions options = FirefoxUtils.getFirefoxOptions();
        try {
            return new FirefoxDriver(options);
        } catch (IllegalStateException e) {
            logger.warn(e.getMessage());
            logger.info("Using Firefox driver for Mac");
            System.setProperty("webdriver.gecko.driver"
                    , System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver");
            return new FirefoxDriver(options);
        }
    }

    public static WebDriver getDriver() {
        if (null == driver || driver.toString().contains("(null)")) {
            logger.info("Creating Driver...");
            String browser = System.getProperty("browser") == null
                    ? "chrome" : System.getProperty("browser").toLowerCase();
            switch(browser){
                case "chrome":
                    driver = DriverManager.createChromeDriver();
                    break;
                case "firefox":
                    driver = DriverManager.createFirefoxDriver();
                    break;
                    default:
                        logger.info("Invalid Browser or Browser not Supported !!!!!!!");
            }
            windowHandles = new LinkedHashSet<>();
            windowHandles.add(driver.getWindowHandle());
            wait = new WebDriverWait(driver, 10);
            driver.navigate().to(PropertyUtil.getBaseUrl());
        }
        return driver;
    }

    public static void closeDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            logger.info("Closing browser....");
        } else {
            logger.error("Driver is not initialized or browser was already closed.");
        }
    }

    public static void switchToWindowOpened() {
        try {
            getWait().until(d -> d.getWindowHandles().size() > windowHandles.size());
        } catch (TimeoutException e) {
            throw new NoSuchWindowException("New Window not found.", e);
        }
        driver.switchTo().window(
                driver.getWindowHandles().stream()
                        .filter(
                                handle -> !windowHandles.contains(handle)
                        ).findFirst().orElseThrow(
                        () -> new NoSuchWindowException("New Window not found.")
                )
        );
        windowHandles.add(driver.getWindowHandle());
    }

    public static WebDriverWait getWait() {
        if (wait == null) getDriver();
        return wait;
    }

    public static boolean isAlertPresent() {
        Alert alert = ExpectedConditions.alertIsPresent().apply(getDriver());
        return alert != null;
    }

    public static void switchToFrame(String frameId) {
        DriverManager.getDriver().switchTo().frame(frameId);
    }

    public static Alert getAlert() {
        if (alert != null) return alert;
        throw new WebDriverException("The Alert was not previously handled");

    }
}
