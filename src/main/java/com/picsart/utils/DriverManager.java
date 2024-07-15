package com.picsart.utils;

import static com.picsart.utils.LogManager.log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

  private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

  public static DriverManager get() {
    return new DriverManager();
  }

  public WebDriver getDriver() {
    if (drivers.get() == null) {
      WebDriver driver = new ChromeDriver();
      drivers.set(driver);
    }
    return drivers.get();
  }

  public void quitDriver(WebDriver driver) {
      log.info("Closing the driver");
      driver.quit();
      drivers.remove();
    }
}
