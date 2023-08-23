package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SKIP_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']";
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, '{TITLE}')]/following-sibling::*[contains(@text,'{DESCRIPTION}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
        SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_RESULT_BY_ORDER_NUMBER_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][{NUMBER}]";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }


}
