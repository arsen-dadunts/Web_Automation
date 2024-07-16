package com.picsart.pages;

import com.picsart.base.BasePage;
import com.picsart.utils.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class HomePage extends BasePage<HomePage> {

  @FindBy(css = "a[data-testid='search-icon-test']")
  private WebElement searchIcon;

  @Override
  protected String getUrl() {
    return "/";
  }

  @Override
  protected void isLoaded() throws Error {
    if (!DriverManager.get().getDriver().getCurrentUrl().startsWith(BASE_URL)) {
        throw new Error();
    }
    waitForElementToBeVisible(searchIcon);
  }

  public SearchPage clickSearchIcon() {
    log.info("Click 'Search' icon");
    searchIcon.click();
    return new SearchPage().get();
  }
}
