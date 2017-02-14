package com.rocketmiles.search.generic;

import com.rocketmiles.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vibha on 2/13/2017.
 */
public class SearchDate {
    private HomePage homePage;
    private final WebDriver driver;

    public SearchDate(WebDriver driver) {
        this.driver = driver;
    }

    public void setSearchStartDate(String year, String month, String date) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.checkinDateField.click();

        //set year
        while (!homePage.currentYear.getText().equals(year)) {
            homePage.datePickerNext.click();
        }

        //set month
        while (!homePage.currentMonth.getText().equals(month)) {
            homePage.datePickerNext.click();
        }

        //set date
        for (WebElement el : homePage.checkinDates) {
            if (el.getText().equals(date)) {
                el.click();
                break;
            }
        }
    }

    public void setSearchEndDate (String year, String month, String date) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.checkoutDateField.click();

        //set year
        while (!homePage.currentYear.getText().equals(year)) {
            homePage.datePickerNext.click();
        }

        //set month
        while (!homePage.currentMonth.getText().equals(month)) {
            homePage.datePickerNext.click();
        }

        //set date
        for (WebElement el : homePage.checkoutDates) {
            if (el.getText().equals(date)) {
                el.click();
                break;
            }
        }
    }
}