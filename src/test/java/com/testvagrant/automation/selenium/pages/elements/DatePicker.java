package com.testvagrant.automation.selenium.pages.elements;


import com.testvagrant.automation.net.DriverManager;
import com.testvagrant.automation.selenium.SeleniumFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class DatePicker {

    private static final Logger logger = Logger.getLogger(DatePicker.class);

    private By calenderIcon = By.className("datePicker");
    private By today = By.className("ui-state-highlight");
    private By CALENDER = By.xpath("//table/tbody");
    private WebDriver driver = DriverManager.getDriver();

    public void searchFlightsForToday(){
        SeleniumFunctions.Click(calenderIcon);
        SeleniumFunctions.Click(today);

    }

    public void searchFlightsForOtherDay(String date){
        //to be defined
    }

    public void selectFirstActiveDate() {
        List<WebElement> tds = null;
        WebElement table = null;
        String highLightedDate = " ui-datepicker-days-cell-over  selected";
        for(int i =0 ; i<3; i++) {
            try {
                table = driver.findElement(CALENDER);
                break;
            } catch (Exception e) { logger.info("StaleExceptionCaught"+e.getMessage());
            }
        }
        for(WebElement td : table.findElements(By.tagName("td"))){
            if(td.getAttribute("class").equals(highLightedDate)) {
                logger.info(td.getAttribute("class"));
                td.click();
                break;
            }

        }
    }
}
