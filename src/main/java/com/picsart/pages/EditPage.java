package com.picsart.pages;

import com.picsart.base.BasePage;
import com.picsart.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPage extends BasePage<EditPage> {

  @FindBy(css = "div[data-test='canvas-container']")
  private WebElement canvasContainer;

  @FindBy(xpath = "//div[@data-testid='canvas-wrapper']/following-sibling::*/div[@data-testid='transparent-background-image']")
  private WebElement image;

  @Override
  protected String getUrl() {
    return "/create/editor";
  }

  @Override
  protected void isLoaded() throws Error {
    if (!DriverManager.get().getDriver().getCurrentUrl().startsWith(BASE_URL + getUrl())) {
      throw new Error("Invalid URL");
    }
    waitForElementToBeVisible(canvasContainer);
  }

  public boolean isImageDisplayed() {
    return image.isDisplayed();
  }
}
