package com.uptake.navigation;

import com.uptake.base.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.*;

import com.uptake.page.HomePage;

public class NavigationTests extends BaseTest {

  private HomePage homepage;

  @BeforeMethod
  public void beforeMethod() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test(dataProvider = "NavigationMenuItems")
  public void testDesktopMenuBarNavigation(int index, String title, String urlPath) {
    //store window handle
    String originalWindow = driver.getWindowHandle();

    //click on menu item
    clickAndWaitForPage(homepage.menuItems.get(index));
    //TODO remove this explicit wait
    waitForNumberOfSeconds(2);

    if (index !=5) {
      //verify page title and url
      Assert.assertEquals(driver.getTitle(), title, "Unexpected page title.");
      Assert.assertEquals(driver.getCurrentUrl(), "https://uptake.com" + urlPath, "Unexpected URL");
    } else {
      //switch to new window
      for(String winHandle : driver.getWindowHandles()){
        driver.switchTo().window(winHandle);
      }
      //verify page title and url
      Assert.assertEquals(driver.getTitle().substring(0, 11), title, "Unexpected page title.");
      Assert.assertEquals(driver.getCurrentUrl(), urlPath, "Unexpected URL");
      driver.close();
      driver.switchTo().window(originalWindow);
    }
    clickAndWaitForPage(homepage.mainLogo);
    //TODO remove this explicit wait
    waitForNumberOfSeconds(2);
  }

  @Test(dataProvider = "NavigationMenuItems")
  public void testMobileDesktopMenuBarNavigation(int index, String title, String urlPath) {
    //store window handle
    String originalWindow = driver.getWindowHandle();

    //resize window to expose mobile header
    Dimension dim = new Dimension(1024, 900);
    driver.manage().window().setSize(dim);

    //click hamburger menu to expose menu options
    homepage.hamburgerLink.click();
    waitForNumberOfSeconds(1);

    //click on menu item
    homepage.mobileMenuItems.get(index).click();
    //TODO remove this explicit wait
    waitForNumberOfSeconds(2);

    if (index !=5) {
      //verify page title and url
      Assert.assertEquals(driver.getTitle(), title, "Unexpected page title.");
      Assert.assertEquals(driver.getCurrentUrl(), "https://uptake.com" + urlPath, "Unexpected URL");
    } else {
      //switch to new window
      for(String winHandle : driver.getWindowHandles()){
        driver.switchTo().window(winHandle);
      }
      //verify page title and url
      Assert.assertEquals(driver.getTitle().substring(0, 11), title, "Unexpected page title.");
      Assert.assertEquals(driver.getCurrentUrl(), urlPath, "Unexpected URL");
      driver.close();
      driver.switchTo().window(originalWindow);
    }
  }

  @DataProvider(name = "NavigationMenuItems")
  public static Object[][] menuItems() {
    return new Object[][] {
            {0, "Approach", "/approach"},
            {1, "Products", "/products"},
            {2, "Industries", "/industries"},
            {3, "Newsroom", "/newsroom"},
            {4, "Beyond.Uptake", "/beyond-uptake"},
            {5, "Uptake Blog", "https://blog.uptake.com/"}
    };
  }
}