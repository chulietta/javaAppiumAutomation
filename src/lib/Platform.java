package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private final static String PLATFORM_IOS = "ios";
    private final static String PLATFORM_ANDROID = "android";
    private final static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub/";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance() {
        if (instance ==null) {
            instance = new Platform();
        }
        return instance;
    }


    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value = " + this.getPlatformVar());
        }
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/yuliya/Desktop/JavaAppiumAutomation/apks/org.wikipedia_50447_apps.evozi.com.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 13");
        capabilities.setCapability("platformVersion", "16.4");
        capabilities.setCapability("app", "/Users/yuliya/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("automationName", "Appium");
        return capabilities;
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
