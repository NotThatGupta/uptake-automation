package com.rocketmiles.search.generic;

import com.rocketmiles.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vibha on 2/13/2017.
 */
public class SearchLocation {
    public HomePage homePage;
    private final WebDriver driver;

    public SearchLocation (WebDriver driver) { this.driver = driver; }

    public void selectCurrentLocation() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.locationInput.click();
        homePage.currentLocation.click();
    }

    public void selectDefaultLocation (DEFAULT_LOCATIONS defaultLocation) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.locationInput.click();
        for (WebElement el: homePage.locationSuggestions) {
            if (el.getText().equals(defaultLocation.getDisplayName())) {
                el.click();
                break;
            }
        }
    }

    public void selectCustomLocation (String locationSearch) {

    }
}
