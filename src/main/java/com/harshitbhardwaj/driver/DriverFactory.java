package com.harshitbhardwaj.driver;

import com.harshitbhardwaj.enums.RunMode;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.harshitbhardwaj.config.ConfigurationManager.configuration;

/**
 * A factory class to initialize WebDriver instances based on the browser name and run mode.
 * <p>
 * Supported Browsers:
 * <ul>
 *   <li>Chrome</li>
 *   <li>Firefox</li>
 *   <li>Edge</li>
 * </ul>
 * <p>
 * Supported Run Modes:
 * <ul>
 *   <li>LOCAL - Launches WebDriver on the local machine.</li>
 *   <li>GRID - Connects to a Selenium Grid hub.</li>
 * </ul>
 * <p>
 * This class is designed as a utility and cannot be instantiated.
 *
 * @author Harshit Bhardwaj
 */
public class DriverFactory {

    /**
     * Private constructor to prevent instantiation of the class.
     * Throws an {@link UnsupportedOperationException()} if someone tries to instantiate the class.
     */
    private DriverFactory() {
        throw new UnsupportedOperationException("Can't instantiate DriverFactory class.");
    }

    /**
     * Initializes a WebDriver instance based on the specified browser and run mode.
     * <p>
     * For {@code RunMode.GRID}, it connects to a Selenium Grid hub using the configured grid URL and port.
     * For {@code RunMode.LOCAL}, it creates a local WebDriver instance.
     *
     * @param browserName The browser name (e.g., "chrome", "firefox", "edge"). Defaults to Chrome if null.
     * @param runMode     The run mode (LOCAL or GRID).
     */
    public static void initDriver(String browserName, RunMode runMode) {
        String browser = browserName == null ? "CHROME" : browserName.toUpperCase();
        WebDriver driver = null;

        if (runMode == RunMode.GRID) {
            driver = createRemoteDriver(browser);
        } else {
            driver = createLocalDriver(browser);
        }

        System.out.println("Browser: " + browser);
        System.out.println("RunMode: " + runMode);
        System.out.println("Grid URL: " + configuration().gridUrl());
        System.out.println("Grid Port: " + configuration().gridPort());

        // Store the driver in WebDriverManager (ThreadLocal context)
        WebDriverManager.setDriver(driver);
    }

    /**
     * Creates a local WebDriver instance for the given browser.
     *
     * @param browser The browser name (CHROME, FIREFOX, EDGE).
     * @return Local {@link WebDriver} instance.
     * @throws IllegalArgumentException If the browser is unsupported.
     */
    private static WebDriver createLocalDriver(String browser) {
        return switch (browser) {
            case "CHROME" -> new ChromeDriver((ChromeOptions) BrowserOptions.CHROME.getOptions());
            case "FIREFOX" -> new FirefoxDriver((FirefoxOptions) BrowserOptions.FIREFOX.getOptions());
            case "EDGE" -> new EdgeDriver((EdgeOptions) BrowserOptions.EDGE.getOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    /**
     * Creates a remote WebDriver instance connected to a Selenium Grid hub.
     * <p>
     * Uses the grid URL and port defined in {@code general.properties}.
     *
     * @param browser The browser name (CHROME, FIREFOX, EDGE).
     * @return Remote {@link WebDriver} instance connected to Grid.
     * @throws RuntimeException If the grid URL is malformed or the browser is unsupported.
     */
    private static WebDriver createRemoteDriver(String browser) {
        try {
            // Get browser-specific capabilities
            MutableCapabilities capabilities = BrowserOptions.valueOf(browser).getOptions();

            // Build Selenium Grid URL
            String gridUrl = String.format("http://%s:%s/wd/hub", configuration().gridUrl(), configuration().gridPort());

            // Initialize RemoteWebDriver
            // WebDriver driver = new RemoteWebDriver(new URL(gridUrl), capabilities);

            // Store the driver in WebDriverManager (ThreadLocal context)
            // WebDriverManager.setDriver(driver);
            return new RemoteWebDriver(new URL(gridUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Invalid grid URL: " + configuration().gridUrl() + ":" + configuration().gridPort(), e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unsupported browser: " + browser, e);
        }
    }
}
