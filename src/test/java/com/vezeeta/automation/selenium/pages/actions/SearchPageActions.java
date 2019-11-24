package com.vezeeta.automation.selenium.pages.actions;

import com.vezeeta.automation.selenium.SeleniumFunctions;
import com.vezeeta.automation.selenium.modules.WebSearchResults;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * created by sahil.kashyap on 21/01/19
 */
public class SearchPageActions {

    private static final Logger logger = Logger.getLogger(SearchPageActions.class);

    private By TEXT_INPUT = By.name("q");
    private By SEARCH_BTN = By.cssSelector("input[value='Google Search']");
    private By SEARCH_RESULTS = By.xpath("//*[text()='Search Results']");

    public void performSearch(String keyWord) {
        SeleniumFunctions.EnterText(TEXT_INPUT, keyWord);
        SeleniumFunctions.Click(SEARCH_BTN);
        SeleniumFunctions.waitUntil(SEARCH_RESULTS);
    }

    public void printTitlesAndSections() {
        WebSearchResults  webSearchResults = new WebSearchResults();
        List<WebElement> titles = webSearchResults.getTitles();
        List<WebElement> sections = webSearchResults.getSections();
        if(titles.size()!=0) {
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(i + "th TITLE : " + titles.get(i).getText());
                System.out.println(i + "th SECTION : " + sections.get(i).getText());
                System.out.println("************************");
                System.out.println();
            }
        } else {
            System.out.println("No Web Results Found");
        }

    }
}
