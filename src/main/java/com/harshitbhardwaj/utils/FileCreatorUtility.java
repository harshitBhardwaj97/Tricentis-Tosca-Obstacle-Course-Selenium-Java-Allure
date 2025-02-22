package com.harshitbhardwaj.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.harshitbhardwaj.utils.StringUtils.sanitizeObstacleName;

/**
 * This utility class is responsible for automating the process of navigating through obstacle course pages
 * and generating corresponding page and test files for each obstacle.
 * <p>
 * It does the following:
 * <ul>
 *     <li>Visits each page of obstacles.</li>
 *     <li>Scrapes obstacle IDs and names from the web page.</li>
 *     <li>Sanitizes the obstacle names.</li>
 *     <li>Maps obstacle IDs to their corresponding page and test class names.</li>
 *     <li>Creates the page and test class files using the `FileOrDirectoryCreator` classes.</li>
 * </ul>
 * <p>
 * This class uses the ChromeDriver for web automation and navigates through pages by dynamically generating the URLs
 * based on a page template. It also collects obstacle information (IDs and names) and stores them in
 * a map for file creation.
 * The generated files are for the obstacle pages and their corresponding test classes.
 * <p>
 * The process runs for a total of 4 available pages of obstacles and generates the files accordingly.
 * <p>
 * Usage:
 * <pre>
 *     FileCreatorUtility.main(new String[]{});
 * </pre>
 * </p>
 *
 * @author Harshit Bhardwaj
 */
public class FileCreatorUtility {

    /**
     * Main method to initiate the process of scraping obstacle information from the web and generating files.
     *
     * @param args Command-line arguments (unused).
     * @throws InterruptedException If the thread is interrupted during the sleep time at the end of the execution.
     */
    public static void main(String[] args) throws InterruptedException {
        // Initialize the ChromeDriver for web automation
        var driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Template URL for navigating through obstacle pages
        final String pageUrlTemplate = "https://obstaclecourse.tricentis.com/Obstacles/List?page=%d";

        // XPath templates for locating obstacle IDs and names on the page
        final By currentObstacleIdTemplate = By
                .xpath("//table[contains(@class,'table table-hover')]/tbody/tr/td[1]");
        final By currentObstacleNameTemplate = By
                .xpath("//table[contains(@class,'table table-hover')]/tbody/tr/td[2]");

        // This map will store the obstacle ID as the key and a list of class names as the value
        Map<Integer, List<String>> obstacleIdNameMap = new HashMap<>();

        // Loop through pages 1 to 4 to scrape obstacle information
        for (int i = 1; i <= 4; i++) {
            String url = String.format(pageUrlTemplate, i);
            driver.get(url);
            System.out.println("Navigated to " + url);
            // Fetch obstacles' IDs and Names from the page
            System.out.println("Fetching the obstacles of this page");

            var obstacles = driver.findElements(currentObstacleIdTemplate);
            var obstacleIds = obstacles.stream().map(e -> Integer.parseInt(e.getText())).toList();

            var names = driver.findElements(currentObstacleNameTemplate);
            var sanitizedNames = names.stream().map(name -> sanitizeObstacleName(name.getText())).toList();

            // Map each obstacle ID to its corresponding sanitized page and test class names
            for (int j = 0; j < obstacleIds.size(); j++) {
                List<String> nameList = obstacleIdNameMap.getOrDefault(obstacleIds.get(j), new ArrayList<>());

                // Add "Page" and "Test" suffix to the sanitized name
                String pageClassName = sanitizedNames.get(j) + "Page";
                String testClassName = sanitizedNames.get(j) + "Test";

                nameList.add(pageClassName);
                nameList.add(testClassName);

                obstacleIdNameMap.put(obstacleIds.get(j), nameList);
            }

            // Print out the map for this page and create corresponding page and test files
            obstacleIdNameMap.forEach((id, nameList) -> {
                System.out.println("ID: " + id + ", File Names: " + nameList);
                String testName = "test" + nameList.get(0).replace("Page", "");

                FileOrDirectoryCreator.PageFileCreator.createPageClass(nameList.get(0), String.valueOf(id));
                FileOrDirectoryCreator.TestFileCreator.createTestClass(nameList.get(1), nameList.get(0),
                        StringUtils.createTestName(nameList.get(0)), String.valueOf(id), testName);
            });

            System.out.println("=======================");
        }

        Thread.sleep(2000);

        // Quit the WebDriver instance
        driver.quit();
    }
}