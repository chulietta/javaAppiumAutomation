package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeInSeconds);
        element.clear();
        return element;
    }

    public boolean assertElementHasText(By by, String expected_value, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.textToBe(by, expected_value));
    }

    public boolean assertElementContainsText(By by, String expected_value, String error_message, long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.attributeContains(by, "text", expected_value));
    }

    public void swipeUp(int timeOfSwipe) {
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

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
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

    public void swipeElementToLeft(By by, String error_message) {
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

    public int getAmountElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message) {
        int amountOfElements = getAmountElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element" + by.toString() + "supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementPresent(By by, String error_message) {
        int amountOfElements = getAmountElements(by);
        if (amountOfElements == 0) {
            String default_message = "An element" + by.toString() + "supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
