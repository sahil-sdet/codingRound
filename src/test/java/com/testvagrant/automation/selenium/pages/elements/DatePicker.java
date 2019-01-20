package com.testvagrant.automation.selenium.pages.elements;


import com.testvagrant.automation.selenium.SeleniumFunctions;
import org.openqa.selenium.By;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class DatePicker {

    private By calenderIcon = By.className("datePicker");
    private By today = By.className("ui-state-highlight");

    public void searchFlightsForToday(){
        SeleniumFunctions.Click(calenderIcon);
        SeleniumFunctions.Click(today);

    }

    public void searchFlightsForOtherDay(String date){
        //to be defined
    }
}
