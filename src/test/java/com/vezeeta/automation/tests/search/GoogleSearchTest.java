package com.vezeeta.automation.tests.search;

import com.vezeeta.automation.net.DriverManager;
import com.vezeeta.automation.selenium.pages.actions.SearchPageActions;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * created by sahil.kashyap on 25/11/19
 */
public class GoogleSearchTest {
    private static Logger logger = Logger.getLogger(GoogleSearchTest.class);

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext){
        DriverManager.beforeSuite(iTestContext);
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestContext){
        logger.info("Execution started for "+iTestContext.getName());
    }

    @Test
    public void testGoogleSearch() {
        SearchPageActions searchPageActions = new SearchPageActions();
        searchPageActions.performSearch("programing");
        searchPageActions.printTitlesAndSections();
    }

    @AfterMethod
    public void afterTest(ITestResult iTestResult){
        logger.info("Execution compeleted for "+iTestResult.getName());
        if(iTestResult.getStatus()==ITestResult.FAILURE) {
            logger.info(iTestResult.getName()+"Test Failed.Capturing ScreenShot !!!");
        }}

}
