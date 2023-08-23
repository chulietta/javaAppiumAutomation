package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            ARTICLE_BY_TITLE_TPL,
            CLOSE_SYNC_POPUP,
            SWIPE_ACTION_DELETE_BUTTON;

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article with title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article with title " + article_title
        );
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    SWIPE_ACTION_DELETE_BUTTON,
                    "Cannot find and click delete button",
                    10
            );
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void clickArticleTitleInMyList(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot navigate to article",
                5
        );
    }

    public void closeSyncPopup() {
        this.waitForElementAndClick(
                CLOSE_SYNC_POPUP,
                "Cannot close sync popup",
                5
        );
    }
}
