package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private final static String
            TITLE = "//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::*[1]",
            FOOTER_ELEMENT = "//android.view.View[@content-desc='View article in browser']",
            SAVE_TO_MY_LIST_BUTTON = "org.wikipedia:id/page_save",
            ADD_TO_LIST_LINK = "//*[contains(@text, 'Add to list')]",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            MY_LIST_TITLE_TPL = "//*[contains(@text, '{TITLE}')]";

    private static String getMyListByTitle(String title) {
        return MY_LIST_TITLE_TPL.replace("{TITLE}", title);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("name");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find end of the article",
                15
        );
    }
    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(SAVE_TO_MY_LIST_BUTTON),
                "Cannot find button to save article",
                5
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_LINK),
                "Cannot find Add to List link",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into article text input",
                15
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press Ok button",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder) {
        String my_list_xpath = getMyListByTitle(name_of_folder);
        this.waitForElementAndClick(
                By.id(SAVE_TO_MY_LIST_BUTTON),
                "Cannot find button to save article",
                5
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_LINK),
                "Cannot find Add to List link",
                5
        );
        this.waitForElementAndClick(
                By.xpath(my_list_xpath),
                "Cannot choose list with title " + name_of_folder,
                5
        );
    }

    public void assertTitlePresent() {
        this.assertElementPresent(
                By.xpath(TITLE),
                "Cannot find article description immediately after open"
        );
    }

}
