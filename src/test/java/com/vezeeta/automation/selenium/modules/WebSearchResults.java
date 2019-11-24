package com.vezeeta.automation.selenium.modules;

import com.vezeeta.automation.net.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * created by sahil.kashyap on 24/11/19
 */
public class WebSearchResults {

    private WebDriver driver = DriverManager.getDriver();
    private WebElement webSearchResults = driver.findElement(By.xpath("//*[text()='Web results']/ancestor::div[@class='bkWMgd']"));
    private By titles = By.className("r");
    private By sections = By.className("s");

    public List<WebElement> getTitles() {
        return webSearchResults.findElements(titles);
    }

    public List<WebElement> getSections() {
        return webSearchResults.findElements(sections);
    }
}
