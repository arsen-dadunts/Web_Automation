package com.picsart.base;

import com.picsart.utils.DriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {

  protected static final String BASE_URL = System.getProperty("base.url");

  private final WebDriver driver;
  private final WebDriverWait wait;

  public BasePage() {
    this.driver = DriverManager.get().getDriver();
    this.wait = new WebDriverWait(this.driver,  Duration.ofSeconds(10));
    init();
  }

  @Override
  protected void load() {
    driver.get(BASE_URL + getUrl());
  }

  protected void init() {
    PageFactory.initElements(driver, this);
  }

  protected abstract String getUrl();


  protected void waitForElementToBeVisible(WebElement element) {
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  protected void waitForElementToBeClickable(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  protected void waitForFrameToBeAvailableAndSwitchToIt(By by) {
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
  }

  protected void switchToMainContent() {
    driver.switchTo().defaultContent();
  }

  protected void hoverOverElement(WebElement element) {
    Actions action = new Actions(driver);
    action.moveToElement(element).perform();
  }

  protected void scrollIntoView(WebElement element, boolean alignToTop) {
    getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(arguments[1]);",
        element, alignToTop);
  }

  protected void scrollIntoViewAndHover(WebElement element) {
    scrollIntoView(element, true);
    hoverOverElement(element);
  }

  protected boolean isChildDisplayed(WebElement element, By locator) {
    try {
      element.findElement(locator).isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  protected JavascriptExecutor getJavascriptExecutor() {
    return (JavascriptExecutor) driver;
  }
}
