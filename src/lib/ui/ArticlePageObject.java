package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

import static lib.ui.NavigationUI.HOME_LINK;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_TO_MY_LIST_BUTTON,
            ADD_TO_LIST_LINK,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_LIST_TITLE_TPL;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getMyListByTitle(String title) {
        return MY_LIST_TITLE_TPL.replace("{TITLE}", title);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("name");
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find end of the article",
                    60
            );
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find end of the article",
                    60
            );
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_TO_MY_LIST_BUTTON,
                "Cannot find button to save article",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_LIST_LINK,
                "Cannot find Add to List link",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article text input",
                15
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press Ok button",
                5
        );
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(
                SAVE_TO_MY_LIST_BUTTON,
                "Cannot find button to save article",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                HOME_LINK,
                "Cannot find 'W' button to close article",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder) {
        String my_list_xpath = getMyListByTitle(name_of_folder);
        this.waitForElementAndClick(
                SAVE_TO_MY_LIST_BUTTON,
                "Cannot find button to save article",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_LIST_LINK,
                "Cannot find Add to List link",
                5
        );
        this.waitForElementAndClick(
                my_list_xpath,
                "Cannot choose list with title " + name_of_folder,
                5
        );
    }

    public void assertTitlePresent() {
        this.assertElementPresent(
                TITLE,
                "Cannot find article description immediately after open"
        );
    }

}
