package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private final static String
            STEP_LEARN_MORE_LINK = "name:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "name:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "name:Learn more about data collected",
            NEXT_LINK = "name:Next",
            GET_STARTED_BUTTON = "name:Get started",
            SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' text", 10);
    }

    public void waitForAddOrEditPreferredLanguagesText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' button", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 10);
    }
}
