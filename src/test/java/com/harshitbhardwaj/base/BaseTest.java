package com.harshitbhardwaj.base;

import com.harshitbhardwaj.driver.DriverFactory;
import com.harshitbhardwaj.driver.WebDriverManager;
import com.harshitbhardwaj.enums.RunMode;
import com.harshitbhardwaj.listener.AllureReportHelper;
import com.harshitbhardwaj.support.CommonPageOperations;
import com.harshitbhardwaj.support.PageInteractionHelper;
import org.testng.SkipException;
import org.testng.annotations.*;

import static com.harshitbhardwaj.config.ConfigurationManager.configuration;

/**
 * BaseTest is the base class for all test classes. It provides common setup and tearDown methods
 * for managing WebDriver and initializing page object helpers.
 * <p>
 * The {@link #setup(String)} ()} method initializes the WebDriver based on the browser passed in xml file. It also
 * creates instances of page object helpers
 * like {@link CommonPageOperations} and {@link PageInteractionHelper}.
 * <p>
 * The {@link #tearDown()} method is responsible for quitting the WebDriver session after each test.
 *
 * @author Harshit Bhardwaj
 */
public class BaseTest {

    // Page object helpers
    private static final ThreadLocal<CommonPageOperations> threadPage = new ThreadLocal<>();
    private static final ThreadLocal<PageInteractionHelper> threadPageHelper = new ThreadLocal<>();
    
    protected CommonPageOperations getPage() {
        if (null == threadPage.get()) {
            throw new IllegalStateException();
        }
        return threadPage.get();
    }

    protected PageInteractionHelper getPageHelper() {
        if (null == threadPageHelper.get()) {
            throw new IllegalStateException();
        }
        return threadPageHelper.get();
    }

    /**
     * Initializes Allure environment information before the test suite starts.
     * This method is annotated with {@link BeforeSuite} and is executed once
     * before the suite of tests begins.
     *
     * @see AllureReportHelper#setAllureEnvironmentInformation()
     */
    @BeforeSuite
    public void beforeSuite() {
        AllureReportHelper.setAllureEnvironmentInformation();
    }

    /**
     * Sets up the WebDriver and page helpers before each test. Initializes
     * WebDriver based on the browser defined in the configuration file. If the
     * browser is not defined or invalid, the test will be skipped.
     *
     * @throws SkipException if the browser is not configured properly or is
     *                       invalid.
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        try {
            String runModeStr = configuration().runMode(); // "LOCAL" or "GRID"
            RunMode runModeEnum = RunMode.valueOf(runModeStr.toUpperCase());

            // DriverFactory will init and set driver into ThreadLocal
            DriverFactory.initDriver(browser, runModeEnum);

            // Use the driver directly from WebDriverManager
            WebDriverManager.getDriver().manage().window().maximize();

            // Initialize thread-local page helpers
            threadPageHelper.set(new PageInteractionHelper(WebDriverManager.getDriver()));
            threadPage.set(new CommonPageOperations(WebDriverManager.getDriver()));

            System.out.println("Initialized " + runModeEnum + " driver: " + browser);
        } catch (IllegalArgumentException e) {
            throw new SkipException("Invalid run mode or browser provided: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new SkipException("Failed to initialize driver: " + e.getMessage(), e);
        }
    }

    /**
     * Tears down the WebDriver session after each test. Quits the driver session if
     * it's still running and removes the driver from the thread-local storage.
     */
    @AfterMethod
    public void tearDown() {
        if (WebDriverManager.getDriver() != null) {
            System.out.println("Quitting the session");
            WebDriverManager.quitDriver();
        }
        threadPageHelper.remove();
        threadPage.remove();
    }
}