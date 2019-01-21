package com.testvagrant.automation.tests.hotels;


import com.testvagrant.automation.net.DriverManager;
import com.testvagrant.automation.selenium.SeleniumFunctions;
import com.testvagrant.automation.selenium.pages.actions.HomePageActions;
import com.testvagrant.automation.selenium.pages.actions.HotelsPageActions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class HotelBookingTest {

    private static Logger logger = Logger.getLogger(HotelBookingTest.class);

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext){
        DriverManager.beforeSuite(iTestContext);
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestContext){
        logger.info("Execution started for "+iTestContext.getName());
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        HomePageActions homePageActions = new HomePageActions();
        homePageActions.navigateTo("hotels");
        HotelsPageActions hotelsPageActions = new HotelsPageActions();
        hotelsPageActions.enterLocatlity("Indiranagar Bangalore");
        hotelsPageActions.checkInToday();
        hotelsPageActions.setCheckOutTommorrow();
        hotelsPageActions.selectTravellers("1 room, 2 adults");
        hotelsPageActions.searchHotels();
        hotelsPageActions.waitForSearchResults();
        Assert.assertTrue(hotelsPageActions.isSearchResultsDisplayed());
    }

    @AfterMethod
    public void afterTest(ITestResult iTestResult){
        logger.info("Execution compeleted for "+iTestResult.getName());
        if(iTestResult.getStatus()==ITestResult.FAILURE)
            logger.info(iTestResult.getName()+"Test Failed.Capturing ScreenShot !!!");
    }
}
