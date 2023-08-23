package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        MY_LIST_OPEN_LINK = "id:Saved";
        HOME_LINK = "xpath://XCUIElementTypeNavigationBar[@name=\"W\"]";

    }

    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
