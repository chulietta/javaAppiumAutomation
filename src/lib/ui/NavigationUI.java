package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LIST_OPEN_LINK,
            HOME_LINK;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        this.waitForElementAndClick(
                MY_LIST_OPEN_LINK,
                "Cannot find navigation to My list",
                5
        );
    }
}
