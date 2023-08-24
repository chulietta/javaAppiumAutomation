package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        DESCRIPTION = "id:Automation for Apps";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_TO_MY_LIST_BUTTON = "id:Save for later";
        MY_LIST_TITLE_TPL = "xpath://*[contains(@text, '{TITLE}')]";

    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
