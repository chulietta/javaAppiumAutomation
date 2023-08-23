package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LIST_OPEN_LINK = "xpath://*[contains(@text, 'View list')]";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
