package com.harshitbhardwaj.constants;

import java.io.File;

/**
 * A utility class that holds various constant values used throughout the framework.
 * <p>
 * This class cannot be instantiated.
 * </p>
 *
 * @author Harshit Bhardwaj
 */
public final class Constants {

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Can't instantiate Constants.class");
    }

    /**
     * Utility method to create a path by concatenating multiple path segments.
     *
     * @param paths the segments of the path to be joined
     * @return the full file path as a String
     * @throws IllegalArgumentException if any of the path segments are empty
     */
    private static String createPath(String... paths) {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Paths cannot be empty");
        }

        if (paths.length == 1) return paths[0];

        for (final String path : paths) {
            if (path.isEmpty()) {
                throw new IllegalArgumentException("Path cannot be empty");
            }
        }


        final StringBuilder result = new StringBuilder();
        result.append(paths[0]);
        int i = 1;

        while (i < paths.length) {
            result.append(File.separator);
            result.append(paths[i]);
            i++;
        }
        return result.toString();
    }

    /**
     * Contains common constants used across the framework, such as URLs and wait times.
     * <p>
     * This class cannot be instantiated.
     * </p>
     */
    public static class Common {

        /**
         * The base URL for the application under test.
         */
        public static final String BASE_URL = "https://obstaclecourse.tricentis.com/Obstacles/";

        /**
         * The URL of selenium website to demonstrate the usage of framework concepts
         */
        public static final String SELENIUM_WEBSITE_URL = "https://www.selenium.dev/";

        /**
         * The explicit wait time for longer waits (in seconds).
         */
        public static final int EXPLICIT_LONG_WAIT = 10;

        /**
         * The explicit wait time for shorter waits (in seconds).
         */
        public static final int EXPLICIT_SHORT_WAIT = 5;

        /**
         * The directory path for page classes.
         */
        public static final String PAGES_DIRECTORY =
                createPath("src", "main", "java", "com", "harshitbhardwaj", "pages");

        /**
         * The directory path for test classes.
         */
        public static final String TESTS_DIRECTORY =
                createPath("src", "test", "java", "com", "harshitbhardwaj", "tests");

        /**
         * The base directory of the project. This is fetched using `System.getProperty("user.dir")`,
         * which returns the current working directory of the Java application.
         * This directory can be used to define paths relative to the project root or current working directory.
         */
        public static final String dir = System.getProperty("user.dir");

        /**
         * The directory path for downloaded files. This is the path where files will be downloaded
         * during test execution. It points to the `downloads` directory inside the `resources` folder
         * under the `test` directory.
         * <p>
         * For example: `"src/test/resources/downloads"`.
         */
        public static final String DOWNLOADS_DIRECTORY =
                createPath(dir, "src", "test", "resources", "downloads");

        // Private constructor to prevent instantiation
        private Common() {
            throw new AssertionError("Can't instantiate Constants.Common.class");
        }
    }
}