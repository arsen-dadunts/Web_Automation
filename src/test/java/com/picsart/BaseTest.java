package com.picsart;

import com.picsart.pages.HomePage;
import com.picsart.utils.DriverManager;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

  public HomePage homePage;

  @BeforeTest
  @Parameters({"width", "height"})
  public void setup(int width, int height) {
      DriverManager.get().getDriver().manage().window().setSize(new Dimension(width, height));
  }

  @BeforeClass(description = "Open Home page")
  public void openHomePage() {
    homePage = new HomePage().get();
  }

  @AfterTest(description = "Close driver")
  public void quitDriver() {
    DriverManager.get().quitDriver(DriverManager.get().getDriver());
  }
}
