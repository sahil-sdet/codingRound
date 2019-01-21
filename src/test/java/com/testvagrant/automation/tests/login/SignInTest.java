package com.testvagrant.automation.tests.login;


import com.testvagrant.automation.net.DriverManager;
import com.testvagrant.automation.selenium.pages.actions.HomePageActions;
import com.testvagrant.automation.selenium.pages.actions.LoginPageActions;
import org.apache.log4j.Logger;
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
public class SignInTest {
    private static Logger logger = Logger.getLogger(SignInTest.class);

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext){
        DriverManager.beforeSuite(iTestContext);
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestContext){
        logger.info("Execution started for "+iTestContext.getName());
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        HomePageActions homePageActions = new HomePageActions();
        homePageActions.openLoginPage();
        LoginPageActions loginPageActions = new LoginPageActions();
        loginPageActions.performLogin("", "");
        Assert.assertTrue(loginPageActions.isErrorDisplayed());

    }

    @AfterMethod
    public void afterTest(ITestResult iTestResult){
        logger.info("Execution compeleted for "+iTestResult.getName());
        DriverManager.closeDriver();
        if(iTestResult.getStatus()==ITestResult.FAILURE){
            logger.info(iTestResult.getName()+"Test Failed.Capturing ScreenShot !!!");
            DriverManager.closeDriver(); }
    }
}
