package com.harshitbhardwaj.support;

import org.openqa.selenium.By;

/**
 * Abstract class providing common functionality for test helpers.
 * It provides shared locators and abstract methods that need to be implemented by subclasses.
 *
 * @author Harshit Bhardwaj
 */
public abstract class AbstractTestHelper {

    /**
     * Locator for the "Good job!" heading.
     */
    protected By goodJobHeading = By.xpath("//h2[.='Good job!']");

    /**
     * Locator for the "You solved this automation problem." heading.
     */
    protected By problemSolvedHeading = By.xpath("//p[.='You solved this automation problem.']");

    /**
     * Abstract method to navigate to the obstacle course.
     * Subclasses need to implement the actual navigation logic.
     */
    protected abstract void navigateToObstacle();

    /**
     * Abstract method to check if the test has passed.
     * Subclasses need to implement the logic to determine whether the test passed.
     *
     * @return true if the test passed, false otherwise.
     */
    protected abstract boolean isTestPassed();
}