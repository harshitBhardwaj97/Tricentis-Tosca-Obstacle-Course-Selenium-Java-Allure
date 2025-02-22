package com.harshitbhardwaj.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.HashMap;
import java.util.Map;

import static com.harshitbhardwaj.config.ConfigurationManager.configuration;
import static com.harshitbhardwaj.constants.Constants.Common.DOWNLOADS_DIRECTORY;

/**
 * Factory class to create WebDriver instances for different browsers.
 * This enum supports Chrome, Firefox, Edge, and Safari browsers with options for
 * headless execution and maximized windows.
 *
 * @author Harshit Bhardwaj
 */
public enum BrowserFactory {

    /**
     * Chrome browser configuration.
     */
    CHROME {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.setDriver(new ChromeDriver(getOptions()));
            System.out.println("======== BrowserFactory.CHROME.createDriver() called ========");
            return WebDriverManager.getDriver();
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments(REMOTE_ALLOW_ORIGINS);
            chromeOptions.addArguments(DISABLE_INFOBARS);
            chromeOptions.addArguments(DISABLE_NOTIFICATIONS);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", DOWNLOADS_DIRECTORY);
            chromeOptions.setExperimentalOption("prefs", prefs);
            if (configuration().headless()) {
                chromeOptions.addArguments(CHROME_HEADLESS);
            }
            return chromeOptions;
        }
    },

    /**
     * Firefox browser configuration.
     */
    FIREFOX {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.setDriver(new FirefoxDriver(getOptions()));
            WebDriverManager.getDriver().manage().window().maximize();
            System.out.println("======== BrowserFactory.FIREFOX.createDriver() called ========");
            return WebDriverManager.getDriver();
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("browser.download.dir", DOWNLOADS_DIRECTORY);
            firefoxOptions.addPreference("browser.download.folderList", 2);
            firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
            firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
                    "application/pdf,application/octet-stream");

            // This doesn't work as of now
            // firefoxOptions.addArguments(START_MAXIMIZED);
            if (configuration().headless()) {
                firefoxOptions.addArguments(GENERIC_HEADLESS);
            }
            return firefoxOptions;
        }
    },

    /**
     * Edge browser configuration.
     */
    EDGE {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.setDriver(new EdgeDriver(getOptions()));
            System.out.println("======== BrowserFactory.EDGE.createDriver() called ========");
            return WebDriverManager.getDriver();
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(START_MAXIMIZED);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", DOWNLOADS_DIRECTORY);
            edgeOptions.setExperimentalOption("prefs", prefs);
            if (configuration().headless()) {
                edgeOptions.addArguments(GENERIC_HEADLESS);
            }
            return edgeOptions;
        }
    };

    // Constants for browser options
    public static final String REMOTE_ALLOW_ORIGINS = "--remote-allow-origins=*";
    public static final String DISABLE_INFOBARS = "--disable-infobars";
    public static final String DISABLE_NOTIFICATIONS = "--disable-notifications";
    public static final String CHROME_HEADLESS = "--headless=new";
    public static final String GENERIC_HEADLESS = "-headless";
    private static final String START_MAXIMIZED = "--start-maximized";

    /**
     * Abstract method to create a WebDriver instance.
     *
     * @return the WebDriver instance for the respective browser.
     */
    public abstract WebDriver createDriver();

    /**
     * Abstract method to get the browser-specific options.
     *
     * @return the options for the respective browser.
     */
    public abstract AbstractDriverOptions<?> getOptions();
}