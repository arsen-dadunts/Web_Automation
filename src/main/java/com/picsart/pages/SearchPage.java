package com.picsart.pages;

import com.picsart.base.BasePage;
import com.picsart.utils.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SearchPage extends BasePage<SearchPage> {

  private static final String FRAME = "iframe[data-testid='com.picsart.social.search']";

  private final By crownIcon = By.cssSelector("div[data-testid='premium-icon-root']");
  private final By likeButton = By.cssSelector("button[data-testid='like-button-root']");
  private final By saveButton = By.cssSelector("button[data-testid='save-button-root']");
  private final By tryNowButton = By.cssSelector("button[data-testid='try-now-button-root']");

  @FindBy(id = "filter_icon")
  private WebElement filterButton;

  @FindBy(id = "search-filter-header-clear")
  private WebElement clearAllFilterButton;

  @FindBy(css = "div[data-testid='search-filter-root']")
  private WebElement filtersContainer;

  @FindBy(css = "input[aria-label='licenses-Personal-checkbox']")
  private WebElement personalFilterCheckbox;

  @FindBy(css = "div[data-testid='registration-modal-container']")
  private WebElement signInPopup;

  @FindBy(css = "svg[data-testid='modal-close-icon']")
  private WebElement signInPopupCloseButton;

  @FindBy(css = "div[data-testid='search-card-root']")
  private List<WebElement> searchCard;

  @Override
  protected String getUrl() {
    return "/search/images/";
  }

  @Override
  protected void isLoaded() throws Error {
    if (!DriverManager.get().getDriver().getCurrentUrl().endsWith(getUrl())) {
      throw new Error("Invalid URL");
    }
    waitForElementToBeVisible(filterButton);
  }

  @Override
  protected void init() {
    waitForFrameToBeAvailableAndSwitchToIt(By.cssSelector(FRAME));
    PageFactory.initElements(DriverManager.get().getDriver(), this);
  }

  public List<WebElement> getImagesCards() {
    //Need to be refactored and removed
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Get images elements");
    return searchCard;
  }

  public WebElement getFirstNotPremiumImageCard() {
    return getImagesCards().stream().filter(card ->
        card.findElements(crownIcon).isEmpty()).findFirst().orElseThrow();
  }

  public List<WebElement> getPremiumImagesCards() {
    return getImagesCards().stream()
        .filter(i -> !i.findElements(crownIcon).isEmpty())
        .collect(Collectors.toList());
  }

  public SearchPage openFilter() {
    log.info("Open the filter");
    changeFilterState("open");
    return this;
  }

  public SearchPage closeFilter() {
    log.info("Close the filter");
    changeFilterState("closed");
    return this;
  }

  public SearchPage clearFilter() {
    openFilter();
    waitForElementToBeClickable(clearAllFilterButton);
    log.info("Clear the filter");
    clearAllFilterButton.click();
    closeFilter();
    return this;
  }

  public SearchPage checkPersonalCheckbox() {
    log.info("Check 'Personal' license checkbox");
    scrollIntoView(personalFilterCheckbox, true);
    waitForElementToBeClickable(personalFilterCheckbox);
    personalFilterCheckbox.click();
    return this;
  }

  public SearchPage clickLikeButton(WebElement card) {
    scrollIntoViewAndHover(card);
    log.info("Click 'Like' button");
    card.findElement(likeButton).click();
    switchToMainContent();
    return this;
  }

  public EditPage clickTryNowButton(WebElement card) {
    scrollIntoViewAndHover(card);
    log.info("Click 'Try Now' button");
    card.findElement(tryNowButton).click();
    switchToMainContent();
    return new EditPage().get();
  }

  public SearchPage closeSignInPopup() {
    log.info("Close 'SignIn' popup");
    signInPopupCloseButton.click();
    waitForFrameToBeAvailableAndSwitchToIt(By.cssSelector(FRAME));
    return this;
  }

  public boolean isFiltersDisplayed() {
    return Float.parseFloat(filtersContainer.getCssValue("opacity")) == 1;
  }

  public boolean isCrownIconDisplayed(WebElement card) {
    scrollIntoView(card, true);
    return isChildDisplayed(card, crownIcon);
  }

  public boolean isLikeButtonDisplayed(WebElement card) {
    scrollIntoViewAndHover(card);
    return isChildDisplayed(card, likeButton);
  }

  public boolean isTryNowButtonDisplayed(WebElement card) {
    scrollIntoViewAndHover(card);
    return isChildDisplayed(card, tryNowButton);
  }

  public boolean isSaveButtonDisplayed(WebElement card) {
    scrollIntoViewAndHover(card);
    return isChildDisplayed(card, saveButton);
  }

  public boolean isSignInPopupDisplayed() {
    waitForElementToBeVisible(signInPopup);
    return signInPopup.isDisplayed();
  }

  private void changeFilterState(String state) {
    if (!filterButton.getAttribute("data-automation").equals(state)) {
      filterButton.click();
    }
  }
}
