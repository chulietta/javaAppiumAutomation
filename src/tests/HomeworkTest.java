package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomeworkTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCheckSearchInputPlaceholderText() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Search input has wrong placeholder!",
                5
        );
    }

    @Test
    public void testSearchResultsAfterCancelSearch() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                15
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results are empty",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results not cleared",
                15
        );
    }

    @Test
    public void testSearchResultsHasSearchedWord() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                15
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results are empty",
                15
        );

        MainPageObject.assertElementContainsText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][1]"),
                "Java",
                "First search result not includes 'Java'",
                15
        );
        MainPageObject.assertElementContainsText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][2]"),
                "Java",
                "Second search result not includes 'Java'",
                15
        );
        MainPageObject.assertElementContainsText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][3]"),
                "Java",
                "Third search result not includes 'Java'",
                15
        );
    }

    @Test
    public void testTitleOfArticleVisibleAfterOpen() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by" + search_line,
                15
        );
        MainPageObject.assertElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article title immediately after open"
        );
    }

    @Test
    public void testCheckMyListAfterDeleteArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Cannot find search input",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by 'Java'",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to list')]"),
                "Cannot find Add to List link",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot put text into article text input",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press Ok button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "appium",
                "Cannot find search input",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' title searched by 'Appium'",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to list')]"),
                "Cannot find Add to List link",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Learning programming')]"),
                "Cannot choose my list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'View list')]"),
                "Cannot find navigation to My list",
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article for deleting"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Article not deleted",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Appium']"),
                "Cannot see not deleted article",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Appium']"),
                "Cannot navigate to article",
                5
        );
        WebElement title = MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article title",
                15
        );
        String article_title = title.getAttribute("name");

        assertEquals(
                "We see unexpected title!",
                "Automation for Apps",
                article_title
        );
    }
}
