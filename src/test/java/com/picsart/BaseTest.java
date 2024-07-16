package com.picsart;

import com.picsart.pages.HomePage;
import com.picsart.utils.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Slf4j
public class BaseTest {

  public HomePage homePage;

  @BeforeTest
  @Parameters({"width", "height"})
  public void setup(@Optional("1920") int width, @Optional("1080") int height) {
    DriverManager.get().getDriver().manage().window().setSize(new Dimension(width, height));
  }

  @BeforeClass(description = "Open Home page")
  public void openHomePage() {
    log.info("Open 'Home' page");
    homePage = new HomePage().get();
  }

  @AfterTest(description = "Close driver")
  public void quitDriver() {
    log.info("Closing the driver");
    DriverManager.get().quitDriver(DriverManager.get().getDriver());
  }
}
