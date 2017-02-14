package com.rocketmiles.search.generic;

import com.rocketmiles.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vibha on 2/13/2017.
 */
public class SearchRooms {
    private HomePage homePage;
    private final WebDriver driver;

    public SearchRooms(WebDriver driver) {
        this.driver = driver;
    }

    public void selectNumRooms(int numRooms) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.roomsDropdown.click();
        homePage.roomsDropdownElements.get(numRooms-1).click();
    }
}