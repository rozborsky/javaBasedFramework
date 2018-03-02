package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;  //імпортуємо необхідні бібліотеки
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

/**
 * Created by odiachuk on 13.12.16.
 */
public class DriverProvider {

    static String OS_EXTENTION = (System.getProperty("os.name").toLowerCase().contains("win")) ? ".exe" : "_mac";
    static String FIREFOX_PATH = "drivers/geckodriver" + OS_EXTENTION;
    static String CHROME_PATH = "drivers/chromedriver" + OS_EXTENTION;

    //private static WebDriver instance;
    public static ThreadLocal<WebDriver> instance = new ThreadLocal<WebDriver>();

    static String BROWSER_TYPE;

    static public FirefoxDriver getFirefox() {

        System.setProperty("webdriver.gecko.driver", FIREFOX_PATH);

        DesiredCapabilities caps = DesiredCapabilities.firefox();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        return new FirefoxDriver(caps);

    }

    static public ChromeDriver getChrome(){

        System.setProperty("webdriver.chrome.driver", CHROME_PATH);

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.OFF);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--kiosk");
        chromeOptions.addArguments("--start-maximized");

        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return new ChromeDriver(caps);

    }

    static public RemoteWebDriver getRemoteDriver(){
        final String USERNAME = "romanrozborsky";
        final String ACCESS_KEY = "b7481b3f-3d3e-48a0-b5b0-8d35cf3657b0";

        try {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
//            capabilities.setCapability(CapabilityType.VERSION, "51.0");
            capabilities.setCapability(CapabilityType.PLATFORM, "MAC");

            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.OFF);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

            return new RemoteWebDriver(new URL("http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub"),
                    capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failure forming the URL to create a web driver", e);
        }
    }


    public static WebDriver getDriver() {
        //if (instance == null)
        if (instance.get() == null)
            if (getCurrentBrowserName().equals(BrowserType.FIREFOX)) {
                //instance = getFirefox();
                instance.set(getFirefox());
            }
            else if (getCurrentBrowserName().equals(BrowserType.SAFARI)) {
                instance.set(getRemoteDriver());
            }
            else{
                //instance = getChrome();
                instance.set(getChrome());
            }

        //return instance;
        return instance.get();
    }

    public static void closeDriver(){
        //instance.quit();
        instance.get().quit();
        //instance = null;
        instance.set(null);
    }

    public static String getCurrentBrowserName() {
        if (BROWSER_TYPE == null)
            if (FileIO.getConfigProperty("Driver").equals("firefox"))
                BROWSER_TYPE = BrowserType.FIREFOX;
            else if (FileIO.getConfigProperty("Driver").equals("safari"))
                BROWSER_TYPE = BrowserType.SAFARI;
            else
                BROWSER_TYPE = BrowserType.CHROME;
        return BROWSER_TYPE;
    }
}
