package com.picsart.search;

import static com.picsart.constants.CommonAssertionMessages.BUTTON_IS_DISPLAYED;
import static com.picsart.constants.CommonAssertionMessages.ICON_IS_DISPLAYED;
import static com.picsart.constants.CommonAssertionMessages.IMAGE_IS_DISPLAYED;
import static com.picsart.constants.CommonAssertionMessages.POPUP_IS_DISPLAYED;
import static com.picsart.constants.CommonAssertionMessages.SECTION_IS_DISPLAYED;
import static org.assertj.core.api.Assertions.assertThat;

import com.picsart.BaseTest;
import com.picsart.pages.EditPage;
import com.picsart.pages.SearchPage;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class CustomTest extends BaseTest {

  private SearchPage searchPage;

  @BeforeClass(description = "Navigate to Search.")
  public void navigateToSearchPage() {
    searchPage = homePage.clickSearchIcon();
  }

  @Test(description = "Click on the filter button and verify that the filters disappear.",
      priority = 1)
  public void verifyFiltersDisappearAfterClickingOnIt() {
    if (searchPage.isFiltersDisplayed()) {
      searchPage.closeFilter();
    }
    Assertions.assertThat(searchPage.isFiltersDisplayed())
        .as(SECTION_IS_DISPLAYED, "Filters")
        .isFalse();
  }

  @Test(description = "Choose the \"Personal\" checkbox from the \"License\" section and verify "
      + "that there are no \"PLUS\" assets. Hovering over an asset should display the like, save,"
      + " and try now buttons.", priority = 2)
  public void verifyPersonalFilterFunctionality() {
    searchPage.openFilter().checkPersonalCheckbox().closeFilter();
    verifyVisibilityOfCardElements(searchPage.getImagesCards(), false, true, true, true);
  }


  @Test(description = "Click on the like button and verify that the sign-in popup appears.",
      priority = 3)
  public void clickLikeButtonAndVerifySignInPopupAppears() {
    searchPage.clickLikeButton(searchPage.getFirstNotPremiumImageCard());
    Assertions.assertThat(searchPage.isSignInPopupDisplayed())
        .as(POPUP_IS_DISPLAYED, "Sign In")
        .isTrue();
  }

  @Test(description = "Close the popup, remove the filter, hover over a \"PLUS\" asset and verify "
      + "that only the “try now” button appears.", priority = 4)
  public void hoverOverPlusAssetAndVerifyTryNowButtonAppears() {
    searchPage.closeSignInPopup().clearFilter();
    verifyVisibilityOfCardElements(searchPage.getPremiumImagesCards(), true, false, false, true);
  }

  @Test(description = "Click on the \"try now\" button and verify that the editing screen opens "
      + "with the image applied to the canvas.", priority = 5)
  public void clickTryNowAndVerifyEditingScreenOpens() {
    EditPage editPage = searchPage.clickTryNowButton(searchPage.getFirstNotPremiumImageCard());
    Assertions.assertThat(editPage.isImageDisplayed())
        .as(IMAGE_IS_DISPLAYED, "Canvas applied")
        .isTrue();
  }

  private void verifyVisibilityOfCardElements(List<WebElement> cards, boolean crownIcon,
      boolean likeButton, boolean saveButton, boolean tryNowButton) {
    assertThat(cards)
        .allSatisfy(card -> {
          assertThat(searchPage.isCrownIconDisplayed(card))
              .as(ICON_IS_DISPLAYED, "Crown")
              .isEqualTo(crownIcon);
          assertThat(searchPage.isLikeButtonDisplayed(card))
              .as(BUTTON_IS_DISPLAYED, "Like")
              .isEqualTo(likeButton);
          assertThat(searchPage.isSaveButtonDisplayed(card))
              .as(BUTTON_IS_DISPLAYED, "Save")
              .isEqualTo(saveButton);
          assertThat(searchPage.isTryNowButtonDisplayed(card))
              .as(BUTTON_IS_DISPLAYED, "Try Now")
              .isEqualTo(tryNowButton);
        });
  }
}
