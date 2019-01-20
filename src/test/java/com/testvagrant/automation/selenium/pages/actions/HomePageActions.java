package com.testvagrant.automation.selenium.pages.actions;

import com.testvagrant.automation.net.DriverManager;
import com.testvagrant.automation.selenium.SeleniumFunctions;
import com.testvagrant.automation.selenium.pages.elements.DatePicker;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class HomePageActions {

    private static final Logger logger = Logger.getLogger(HomePageActions.class);

    private By FROM = By.id("FromTag");
    private By TO = By.id("ToTag");
    private By SEARCH_BTN = By.id("SearchBtn");

    public void enterFromAndToCities(String from, String to) {
        SeleniumFunctions.EnterText(FROM, from);
        SeleniumFunctions.EnterText(TO, to);
    }

    public void selectTodayDate(){
        DatePicker datePicker = new DatePicker();
        datePicker.searchFlightsForToday();
    }

    public void searchFlights(){
        SeleniumFunctions.Click(SEARCH_BTN);
    }

    public void waitTillFlightSearchResults(){
        DriverManager.getWait().until(ExpectedConditions.titleContains("Cleartrip |"));
    }

}
