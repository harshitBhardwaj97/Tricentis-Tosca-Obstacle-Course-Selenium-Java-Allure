package com.harshitbhardwaj.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.HashMap;
import java.util.Map;

import static com.harshitbhardwaj.config.ConfigurationManager.configuration;

/**
 * Factory enum to create browser-specific {@link AbstractDriverOptions} instances.
 * <p>
 * Supported browsers:
 * <ul>
 *   <li>Chrome</li>
 *   <li>Firefox</li>
 *   <li>Edge</li>
 * </ul>
 * <p>
 * Each browser configuration supports:
 * <ul>
 *   <li>Headless execution (controlled by configuration)</li>
 *   <li>Maximized windows (where supported)</li>
 *   <li>Custom preferences such as notifications and downloads</li>
 * </ul>
 * <p>
 * Usage:
 * <pre>{@code
 * AbstractDriverOptions<?> options = BrowserOptions.CHROME.getOptions();
 * }</pre>
 * <p>
 * Author: Harshit Bhardwaj
 */
public enum BrowserOptions {

    /**
     * Chrome browser configuration.
     * <p>
     * Adds default options:
     * <ul>
     *   <li>Maximized window</li>
     *   <li>Disable infobars</li>
     *   <li>Disable notifications</li>
     *   <li>Experimental preferences (can be extended for downloads, etc.)</li>
     *   <li>Headless mode if enabled in configuration</li>
     * </ul>
     */
    CHROME {
        @Override
        public ChromeOptions getOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments(REMOTE_ALLOW_ORIGINS);
            chromeOptions.addArguments(DISABLE_INFOBARS);
            chromeOptions.addArguments(DISABLE_NOTIFICATIONS);

            // Set Chrome preferences (e.g., download directory, popups)
            Map<String, Object> prefs = new HashMap<>();
            // Example: prefs.put("download.default_directory", DOWNLOADS_DIRECTORY);
            chromeOptions.setExperimentalOption("prefs", prefs);

            // Enable headless mode if configured
            if (configuration().headless()) {
                chromeOptions.addArguments(CHROME_HEADLESS);
            }
            return chromeOptions;
        }
    },

    /**
     * Firefox browser configuration.
     * <p>
     * Adds default preferences:
     * <ul>
     *   <li>Download folder configuration</li>
     *   <li>Suppress download manager popup</li>
     *   <li>Skip "Save As" dialog for common file types</li>
     * </ul>
     * <p>
     * Notes:
     * <ul>
     *   <li>Start maximized argument currently does not work reliably in Firefox.</li>
     *   <li>Headless mode enabled if configured.</li>
     * </ul>
     */
    FIREFOX {
        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            // Example: firefoxOptions.addPreference("browser.download.dir", DOWNLOADS_DIRECTORY);
            firefoxOptions.addPreference("browser.download.folderList", 2);
            firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
            firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
                    "application/pdf,application/octet-stream");

            // Start maximized is not supported in Firefox (as of now).
            if (configuration().headless()) {
                firefoxOptions.addArguments(GENERIC_HEADLESS);
            }
            return firefoxOptions;
        }
    },

    /**
     * Edge browser configuration.
     * <p>
     * Adds default options:
     * <ul>
     *   <li>Maximized window</li>
     *   <li>Temporary user data directory (to avoid profile conflicts)</li>
     *   <li>Experimental preferences (can be extended for downloads, etc.)</li>
     *   <li>Headless mode if enabled in configuration</li>
     * </ul>
     */
    EDGE {
        @Override
        public EdgeOptions getOptions() {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(START_MAXIMIZED);

            // Set a unique temporary user data directory for Edge to prevent profile conflicts
            String userDataDir = "/tmp/edge-profile-" + java.util.UUID.randomUUID();
            edgeOptions.addArguments("--user-data-dir=" + userDataDir);

            // Preferences map (extendable for downloads, etc.)
            Map<String, Object> prefs = new HashMap<>();
            // Example: prefs.put("download.default_directory", DOWNLOADS_DIRECTORY);
            edgeOptions.setExperimentalOption("prefs", prefs);

            // Enable headless mode if configured
            if (configuration().headless()) {
                edgeOptions.addArguments(GENERIC_HEADLESS);
            }
            return edgeOptions;
        }
    };

    // ================================
    // Common Constants for all browsers
    // ================================

    /**
     * Allow remote origins (required for recent Chrome versions).
     */
    public static final String REMOTE_ALLOW_ORIGINS = "--remote-allow-origins=*";

    /**
     * Disable Chrome/Edge infobars (like "Chrome is being controlled by automated test software").
     */
    public static final String DISABLE_INFOBARS = "--disable-infobars";

    /**
     * Disable browser notifications popups.
     */
    public static final String DISABLE_NOTIFICATIONS = "--disable-notifications";

    /**
     * Headless argument specific to Chrome (new implementation).
     */
    public static final String CHROME_HEADLESS = "--headless=new";

    /**
     * Generic headless argument for Firefox and Edge.
     */
    public static final String GENERIC_HEADLESS = "-headless";

    /**
     * Start browser in maximized mode (supported in Chrome & Edge).
     */
    private static final String START_MAXIMIZED = "--start-maximized";

    /**
     * Abstract method to get the browser-specific options.
     *
     * @return {@link AbstractDriverOptions} configured for the respective browser.
     */
    public abstract AbstractDriverOptions<?> getOptions();
}