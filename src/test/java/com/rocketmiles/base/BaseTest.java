package com.uptake.base;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;

import ru.stqa.selenium.factory.WebDriverPool;

import com.uptake.util.PropertyLoader;
import com.uptake.page.HomePage;

/**
 * Base class for TestNG-based test classes
 */
public class BaseTest {

  protected static String gridHubUrl;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;
  protected HomePage homePage;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    baseUrl = PropertyLoader.loadProperty("site.url");
    gridHubUrl = PropertyLoader.loadProperty("grid.url");
    if (gridHubUrl.equals("")) {
      gridHubUrl = null;
    }
    System.setProperty("webdriver.chrome.driver", "C:\\selenium-drivers\\chromedriver_win32\\chromedriver.exe");
    System.setProperty("webdriver.gecko.driver","C:\\selenium-drivers\\geckodriver-v0.14.0-win64\\geckodriver.exe");
    capabilities = PropertyLoader.loadCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    initialNavigation();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }

  public void initialNavigation() {
    driver.get(baseUrl);
    driver.manage().window().maximize();
    Assert.assertEquals(driver.getTitle(), homePage.windowTitle,
            "Unexpected window title for the uptake.com home page.");
  }

  public void waitForLoad(WebDriver driver) {
    ExpectedCondition<Boolean> pageLoadCondition = new
            ExpectedCondition<Boolean>() {
              public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
              }
            };
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(pageLoadCondition);
  }

  public void waitForNumberOfSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {

    }
  }

  public void clickAndWaitForPage(WebElement element) {
    element.click();
    waitForLoad(driver);
  }
}