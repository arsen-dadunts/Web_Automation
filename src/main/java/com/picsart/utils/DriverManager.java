package com.picsart.utils;

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
      driver.quit();
      drivers.remove();
    }
}
