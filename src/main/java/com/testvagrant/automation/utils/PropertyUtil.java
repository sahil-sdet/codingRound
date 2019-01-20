package com.testvagrant.automation.utils;


import javafx.util.Pair;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * created by sahil.kashyap on 20/01/19
 */
public class PropertyUtil {

    public static Properties properties;
    public static String DynamicElementValue = "";
    public static String propertyFileName;

    public static void loadPropertyFile(String mapFile) {
        properties = new Properties();
        propertyFileName = mapFile;
        try {
            FileInputStream Master = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/UIMap/" + mapFile);
            properties.load(Master);
            Master.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Pair<String, String> getLocator(Enum element) {
        String type;
        String path;
        try {
            String value = properties.getProperty(element.name().toUpperCase());
            String[] values = value.split("_", 2);
            if (values.length == 2) {
                type = values[0].toLowerCase();
                path = values[1];
            }else{
                throw new RuntimeException(String.format("%s value of %s located in %s is bad formatted."
                        , value, element.name(), propertyFileName));
            }
        } catch (NullPointerException e) {
            throw new NoSuchElementException(
                    String.format("'%s' is not defined in %s", element.name(), propertyFileName)
            );
        }
        return new Pair<>(type, path);
    }

    public static By getLocator(String ElementName) {
        String ElementNameValue;
        String locatorType = null;
        String locatorValue;
        try {
            ElementNameValue = properties.getProperty(ElementName);
            String[] values = ElementNameValue.split("_");
            locatorType = values[0];
            locatorValue = values[1];
            if (values.length > 2) {
                locatorValue = "";
                locatorType = values[0];
                for (int i = 1; i < values.length; i++) {
                    locatorValue += values[i];
                }
            }
        } catch (NullPointerException e) {
            throw new NoSuchElementException(ElementName + " is not defined in " + propertyFileName);
        } catch (Exception e) {
            throw new NoSuchElementException("Locator type '" + locatorType + "' not defined in the prop file!!");

        }
        if (locatorValue.contains("%s")) {
            locatorValue = String.format(locatorValue, DynamicElementValue);
        }
        return getLocator(locatorType.toLowerCase(), locatorValue);
    }

    public static String getPropertyValue(String key) {
        if (properties.getProperty(key) == null) {
            throw new NoSuchFieldError("Property key not found in properties file:" + key);
        }
        return properties.getProperty(key);

    }

    public static String getBaseUrl() {
        return getPropertyValue("BASE-URL");
    }

    private static By getLocator(String locatorType, String locatorValue){
        By by = null;
        switch (locatorType){
            case "id" :
                by = By.id(locatorValue);
                break;
            case "classname":
                by = By.className(locatorValue);
                break;
            case "name" :
                by = By.name(locatorValue);
            break;
            case "tagname":
                by = By.tagName(locatorValue);
            break;
            case "linktext" :
                by = By.linkText(locatorValue);
            break;
            case "partiallinktext":
                by = By.partialLinkText(locatorValue);
            break;
            case "cssselector" :
                by = By.cssSelector(locatorValue);
            break;
            case "xpath":
                by = By.xpath(locatorValue);
            break;
            default:
                throw new NoSuchElementException("Locator type '" + locatorType + "' not defined in the prop file!!");
        }
        return by;
    }
}
