package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private final static String
            TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[1]",
            FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']",
            SAVE_TO_MY_LIST_BUTTON = "id:org.wikipedia:id/page_save",
            ADD_TO_LIST_LINK = "xpath://*[contains(@text, 'Add to list')]",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            MY_LIST_TITLE_TPL = "xpath://*[contains(@text, '{TITLE}')]";

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
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find end of the article",
                15
        );
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
