package com.testvagrant.automation.selenium.pages.actions;

import com.testvagrant.automation.selenium.pages.external.LoginModule;
import com.testvagrant.automation.tests.login.SignInTest;
import org.apache.log4j.Logger;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class LoginPageActions {

    private static final Logger logger = Logger.getLogger(SignInTest.class);
    LoginModule loginModule = new LoginModule();
    public void performLogin(String username, String password) {
        loginModule.performLoginAs(username, password);
    }

    public boolean isErrorDisplayed(){
        return loginModule.isErrorDisplayed();
    }

}
