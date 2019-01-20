package com.testvagrant.automation.selenium.pages.elements;

import com.testvagrant.automation.net.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class FlightsResultsList {

    private static final Logger logger = Logger.getLogger(FlightsResultsList.class);

    private By flightsList = By.className("searchSummary");
    private By firstFlight = By.xpath("//nav[@class='listViewNav']/ul/li");

    public boolean isDisplayed() {
        WebDriver driver = DriverManager.getDriver();
        logger.info("Search Results Size :" +driver.findElements(firstFlight).size());
        return driver.findElement(flightsList).isDisplayed();
    }
}
