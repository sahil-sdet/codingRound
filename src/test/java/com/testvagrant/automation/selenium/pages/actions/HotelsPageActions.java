package com.testvagrant.automation.selenium.pages.actions;


import com.testvagrant.automation.net.DriverManager;
import com.testvagrant.automation.selenium.SeleniumFunctions;
import com.testvagrant.automation.selenium.pages.elements.DatePicker;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.List;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class HotelsPageActions {

    private static final Logger logger = Logger.getLogger(HotelsPageActions.class);

    WebDriver driver = DriverManager.getDriver();
    private By CHECK_IN = By.id("CheckInDate");
    private By SEARCH_BTN = By.id("SearchHotelsButton");
    private By TRAVELLERS = By.id("travellersOnhome");
    private By LOCALITY = By.id("Tags");
    private By SEARCH_SUGGESTIONS = By.xpath("//li[@role='presentation']");
    private By SEARCH_RESULTS = By.className("hotelsList");
    private By WAIT_FOR_HOTELS = By.xpath("//*[contains(text(),'Looking for hotels...')]");
    private By WAIT_FOR_PRICE = By.xpath("//*[contains(text(),'Getting you the best rates...')]");

    public void checkInToday() {
        SeleniumFunctions.Click(CHECK_IN);
       new DatePicker().selectFirstActiveDate();
    }

    public void setCheckOutTommorrow() {
        for(int i = 0; i<3; i++) {
            try {
                new DatePicker().selectFirstActiveDate();
                break;
            }catch (StaleElementReferenceException exc){}
        }
    }

    public void selectTravellers(String noOfPersons) {
        SeleniumFunctions.SelectValue(TRAVELLERS, noOfPersons);
    }

    public void enterLocatlity(String area) {
        SeleniumFunctions.EnterText(LOCALITY, area);
        SeleniumFunctions.waitUntil(SEARCH_SUGGESTIONS);
        SeleniumFunctions.Click(SEARCH_SUGGESTIONS);
    }

    public void searchHotels() {
        driver.findElement(SEARCH_BTN).submit();
    }

    public void waitForSearchResults() {
        try {
            SeleniumFunctions.waitUntilInvisibility(WAIT_FOR_HOTELS);
            SeleniumFunctions.waitUntilInvisibility(WAIT_FOR_PRICE);
        } catch (TimeoutException e) {
            logger.info("TimeOut Exception caught");
        }
    }

    public boolean isSearchResultsDisplayed() {
        return driver.findElement(SEARCH_RESULTS).isDisplayed();
    }
}

