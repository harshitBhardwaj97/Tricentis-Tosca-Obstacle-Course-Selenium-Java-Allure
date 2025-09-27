package com.harshitbhardwaj.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

/**
 * This interface defines the configuration properties for the application.
 * The properties are loaded from system properties and a properties file (general.properties).
 * <p>
 * The @LoadPolicy(MERGE) annotation ensures that values from both sources (system properties
 * and classpath properties) are merged, with system properties taking precedence.
 * <p>
 * The properties include the browser configuration, headless mode, and the base URL for the application.
 *
 * @author Harshit Bhardwaj
 */
@LoadPolicy(LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:general.properties"})
public interface Configuration extends Config {

    /**
     * Retrieves the Selenium Grid URL.
     *
     * @return the grid URL as a String.
     */
    @Key("gridUrl")
    @DefaultValue("localhost")
    String gridUrl();

    /**
     * Retrieves the Selenium Grid Port.
     *
     * @return the grid port as a String.
     */
    @Key("gridPort")
    @DefaultValue("4444")
    String gridPort();

    /**
     * Retrieves the run mode for the tests.
     * Default is LOCAL if not specified.
     *
     * @return the run mode (e.g., "LOCAL" OR "GRID").
     */
    @Key("runMode")
    @DefaultValue("LOCAL")
    String runMode();

    /**
     * Retrieves the headless mode setting for the browser.
     *
     * @return true if headless mode is enabled, false otherwise. Defaults to false.
     */
    @Key("headless")
    @DefaultValue("false")
    Boolean headless();
}