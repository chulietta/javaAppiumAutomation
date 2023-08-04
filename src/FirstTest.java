import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/yuliya/Desktop/JavaAppiumAutomation/apks/org.wikipedia_50447_apps.evozi.com.apk");
        capabilities.setCapability("orientation", "PORTRAIT");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                15
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searched by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                15
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Back button",
                5
        );
        waitForElementNotPresent(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Back button is still present",
                5
        );
    }

    @Test
    public void testCompareArticleDescription() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by 'Java'",
                5
        );
        WebElement description_element = waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article description",
                15
        );
        String article_description = description_element.getAttribute("name");

        Assert.assertEquals(
                "We see unexpected description!",
                "Object-oriented programming language",
                article_description
        );
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by 'Java'",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article description",
                15
        );

        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
    }

    @Test
    public void testSwipeArticleToElement() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "appium",
                "Cannot find search input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' title searched by 'Appium'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article description",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@resource-id='pcs-footer-container-readmore-heading']"),
                "Cannot find end of the article",
                15
                //android.view.View[@content-desc="View article in browser"]
        );
    }

    @Test
    public void checkSearchInputPlaceholderText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Search input has wrong placeholder!",
                5
        );
    }

    @Test
    public void testSearchResultsAfterClearSearchInput() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                15
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results are empty",
                15
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results not cleared",
                15
        );
    }

    @Test
    public void testSearchResultsHasSearchedWord() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                15
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results are empty",
                15
        );

        assertElementContainsText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][1]"),
                "Java",
                "First search result not includes 'Java'",
                15
        );
        assertElementContainsText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][2]"),
                "Java",
                "Second search result not includes 'Java'",
                15
        );
        assertElementContainsText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//following::*[@resource-id='org.wikipedia:id/page_list_item_title'][3]"),
                "Java",
                "Third search result not includes 'Java'",
                15
        );
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "java",
                "Cannot find search input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by 'Java'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article description",
                15
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to list')]"),
                "Cannot find Add to List link",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot put text into article text input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press Ok button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'View list')]"),
                "Cannot find navigation to My list",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNonEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "Linkin Park Discography";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                15
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by request" + search_line,
                15
        );
        int amount_of_search_results = getAmountElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue(
                "We found few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "wsxcderfv";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                15
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request",
                15
        );
        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request" + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "java";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by" + search_line,
                15
        );
        String description_before_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find description of article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String description_after_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find description of article",
                15
        );
        Assert.assertEquals(
                "Article description have been change after rotation",
                description_before_rotation,
                description_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String description_after_second_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "name",
                "Cannot find description of article",
                15
        );
        Assert.assertEquals(
                "Article description have been change after rotation",
                description_before_rotation,
                description_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "java",
                "Cannot find search input",
                15
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by 'Java'",
                5
        );
        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background",
                5
        );
    }

    @Test
    public void testTitleOfArticleVisibleAfterOpen() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "java";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' description searched by" + search_line,
                15
        );
        assertElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Cannot find article title immediately after open"
        );
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeInSeconds);
        element.clear();
        return element;
    }

    private boolean assertElementHasText(By by, String expected_value, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.textToBe(by, expected_value));
    }

    private boolean assertElementContainsText(By by, String expected_value, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.attributeContains(by, "text", expected_value));
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swipe = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swipe > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swipe;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void assertElementNotPresent(By by, String error_message) {
        int amountOfElements = getAmountElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element" + by.toString() + "supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    protected void assertElementPresent(By by, String error_message) {
        int amountOfElements = getAmountElements(by);
        if (amountOfElements == 0) {
            String default_message = "An element" + by.toString() + "supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
