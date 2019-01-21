package com.testvagrant.automation.selenium.modules;

import com.testvagrant.automation.selenium.SeleniumFunctions;
import org.openqa.selenium.By;

import java.util.LinkedHashMap;

/**
 * created by sahil.kashyap on 21/01/19
 */
public abstract class BaseFormModule {

    public void setFields(LinkedHashMap<By, String> values) {
        values.forEach((key, value) -> {
                    SeleniumFunctions.EnterText(key,value);
                }
            );

    }
    public void fillAndSubmit(LinkedHashMap<By, String> values) {
        setFields(values);
        SeleniumFunctions.Submit();
    }
}
