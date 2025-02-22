package com.harshitbhardwaj.support;

import org.openqa.selenium.By;

/**
 * Interface that defines helper methods for interacting with the test page.
 * It is meant to be implemented by classes that need to perform specific actions related to the test.
 *
 * @author Harshit Bhardwaj
 */
public interface TestHelper {

    /**
     * Locator for the "Good job!" heading.
     */
    By goodJobHeading = By.xpath("//h2[.='Good job!']");

    /**
     * Locator for the "You solved this automation problem." heading.
     */
    By problemSolvedHeading = By.xpath("//p[.='You solved this automation problem.']");

    /**
     * Navigates to the obstacle course.
     * This method needs to be implemented to specify the navigation logic.
     */
    void navigateToObstacle();

    /**
     * Checks whether the test was passed or not.
     * This method needs to be implemented to define the success criteria of the test.
     *
     * @return true if the test passed, false otherwise.
     */
    boolean isTestPassed();
}