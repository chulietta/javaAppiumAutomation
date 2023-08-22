package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private final static String
            SKIP_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']",
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, '{TITLE}')]/following-sibling::*[contains(@text,'{DESCRIPTION}')]",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']",
            SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list",
            SEARCH_RESULT_BY_ORDER_NUMBER_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][{NUMBER}]";

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

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    //TEMPLATES METHODS

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find result with title " + title + " and description " + description, 15);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Cancel is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SKIP_ELEMENT, "Cannot find Skip button", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find Search Wikipedia input", 5);
    }

    public void clickSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find Search Wikipedia input", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find result with substring " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundedArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request",
                15
        );
        return this.getAmountElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result label by the request", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We found some results by request"
        );
    }

    public void assertSearchInputPlaceholder(String placeholder) {
        this.assertElementHasText(
                SEARCH_INPUT,
                placeholder,
                "Search input has wrong placeholder!",
                5
        );
    }

    public void waitForSearchListIsPresented() {
        this.waitForElementPresent(
                SEARCH_RESULTS_LIST,
                "Search results are empty",
                15
        );
    }

    public void waitForSearchListIsNotPresented() {
        this.waitForElementNotPresent(
                SEARCH_RESULTS_LIST,
                "Search results not cleared",
                15
        );
    }

    public void assertSearchResultContainsSearchRequest(String order_number) {
        String search_result_xpath = getResultSearchElementByOrderNumber(order_number);
        String search_request = "Java";
        this.assertElementContainsText(
                search_result_xpath,
                search_request,
                "Search result " + order_number + " not includes " + search_request,
                15
        );
    }
}
