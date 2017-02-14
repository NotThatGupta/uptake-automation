package com.uptake.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Uptake.com home page
 */

public class HomePage extends BasePage {

  public static String windowTitle = "Analytics for the industrial internet";

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  @FindAll({@FindBy(css = "a.menu__item")})
  public List<WebElement> menuItems;

  @FindAll({@FindBy(css = "a.menu__item-mobile")})
  public List<WebElement> mobileMenuItems;

  @FindBy(css = "a.nav-button.btn.hamburger-link")
  @CacheLookup
  public WebElement hamburgerLink;
}