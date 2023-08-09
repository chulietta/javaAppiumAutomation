package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private final static String
            SKIP_ELEMENT = "//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']",
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
            SEARCH_RESULTS_LIST = "org.wikipedia:id/search_results_list",
            SEARCH_RESULT_BY_ORDER_NUMBER_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][{NUMBER}]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //TEMPLATES METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementByOrderNumber(String number) {
        return SEARCH_RESULT_BY_ORDER_NUMBER_TPL.replace("{NUMBER}", number);
    }
    //TEMPLATES METHODS

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Cancel is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SKIP_ELEMENT), "Cannot find Skip button", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find Search Wikipedia input", 5);
    }

    public void clickSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find Search Wikipedia input", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find result with substring " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundedArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by request",
                15
        );
        return this.getAmountElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result label by the request", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We found some results by request"
        );
    }

    public void assertSearchInputPlaceholder(String placeholder) {
        this.assertElementHasText(
                By.xpath(SEARCH_INPUT),
                placeholder,
                "Search input has wrong placeholder!",
                5
        );
    }
     public void waitForSearchListIsPresented() {
         this.waitForElementPresent(
                 By.id(SEARCH_RESULTS_LIST),
                 "Search results are empty",
                 15
         );
     }

    public void waitForSearchListIsNotPresented() {
        this.waitForElementNotPresent(
                By.id(SEARCH_RESULTS_LIST),
                "Search results not cleared",
                15
        );
    }
    public void assertSearchResultContainsSearchRequest(String order_number) {
        String search_result_xpath = getResultSearchElementByOrderNumber(order_number);
        String search_request = "Java";
        this.assertElementContainsText(
                By.xpath(search_result_xpath),
                search_request,
                 "Search result " + order_number + " not includes " + search_request,
                15
        );
    }
}
