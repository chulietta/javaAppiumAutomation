package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private final static String PLATFORM_IOS = "ios";
    private final static String PLATFORM_ANDROID = "android";

    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub/";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformENV();
        driver = getDriverByPlatformEnv();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private DesiredCapabilities getCapabilitiesByPlatformENV() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/yuliya/Desktop/JavaAppiumAutomation/apks/org.wikipedia_50447_apps.evozi.com.apk");

        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 13");
            capabilities.setCapability("platformVersion", "16.4");
            capabilities.setCapability("app", "/Users/yuliya/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
            capabilities.setCapability("automationName", "Appium");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value = " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformENV();

        if (platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(AppiumURL), capabilities);
        } else {
            throw new Exception("Cannot get driver from env variable. Driver value = " + platform);
        }
        return driver;
    }
}

