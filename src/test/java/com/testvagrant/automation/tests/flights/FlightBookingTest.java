package com.testvagrant.automation.tests.flights;

import com.testvagrant.automation.net.DriverManager;
import com.testvagrant.automation.selenium.pages.actions.HomePageActions;
import com.testvagrant.automation.selenium.pages.elements.FlightsResultsList;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * created by sahil.kashyap on 20/01/19
 */
public class FlightBookingTest {
    private static Logger logger = Logger.getLogger(FlightBookingTest.class);

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
        homePageActions.enterFromAndToCities("Bangalore", "Delhi");
        homePageActions.selectTodayDate();
        homePageActions.searchFlights();
        homePageActions.waitTillFlightSearchResults();
        Assert.assertTrue(new FlightsResultsList().isDisplayed());
    }

    @AfterMethod
    public void afterTest(ITestResult iTestResult){
        logger.info("Execution compeleted for "+iTestResult.getName());
        if(iTestResult.getStatus()==ITestResult.FAILURE)
            logger.info(iTestResult.getName()+"Test Failed.Capturing ScreenShot !!!");
    }

}
