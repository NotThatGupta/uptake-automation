package com.rocketmiles.search.generic;

import com.rocketmiles.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vibha on 2/13/2017.
 */
public class SearchGuests {
    private HomePage homePage;
    private final WebDriver driver;

    public SearchGuests (WebDriver driver) {
        this.driver = driver;
    }

    public void selectNumberOfGuests(int numGuests) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.guestsDropdown.click();
        homePage.guestsDropdownElements.get(numGuests - 1).click();
    }
}
