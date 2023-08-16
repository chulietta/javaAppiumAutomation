package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private final static String
            MY_LIST_OPEN_LINK = "//*[contains(@text, 'View list')]";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OPEN_LINK),
                "Cannot find navigation to My list",
                5
        );
    }
}
