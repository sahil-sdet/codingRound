package com.testvagrant.automation.selenium.pages.external;


import com.testvagrant.automation.net.DriverManager;
import com.testvagrant.automation.selenium.modules.BaseFormModule;
import com.testvagrant.automation.tests.login.SignInTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class LoginModule extends BaseFormModule{

    private static final Logger logger = Logger.getLogger(SignInTest.class);
    private WebDriver driver = DriverManager.getDriver();
    private LinkedHashMap<By, String> credentials = new LinkedHashMap<>();

    private By INPUT_USERNAME = By.id("email");
    private By BUTTON_SUBMIT = By.id("signInButton");
    private By INPUT_PASSWORD = By.id("password");
    private By ERRORS = By.id("errors1");

    public void performLoginAs(String username, String password) {
        credentials.put(INPUT_USERNAME, username);
        credentials.put(INPUT_PASSWORD, password);
        DriverManager.switchToFrame("modal_window");
        fillAndSubmit(credentials);
    }

    public boolean isErrorDisplayed(){
        return driver.findElement(ERRORS).isDisplayed();
    }


}
