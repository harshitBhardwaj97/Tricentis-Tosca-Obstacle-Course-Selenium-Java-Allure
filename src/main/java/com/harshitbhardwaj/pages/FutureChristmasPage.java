package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class FutureChristmasPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By christmasDayInputField = By.cssSelector("input#christmasday");

    public FutureChristmasPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Getting the day of Christmas after two years")
    private String getDayOfChristmasAfterTwoYears() {
        // Get current year
        var today = LocalDate.now();
        var currentYear = today.getYear();
        System.out.println("Current year is: " + currentYear);

        // Target year = current year + 2
        var targetYear = currentYear + 2;
        System.out.println("Target year is: " + targetYear);

        // Get the date of Christmas after two years
        var targetDate = LocalDate.of(targetYear, 12, 25);
        System.out.println("Target date is: " + targetDate);

        // Get the day of week of Christmas after two years and format it with first letter capitalized
        String result = targetDate.getDayOfWeek().toString().charAt(0) +
                targetDate.getDayOfWeek().toString().substring(1).toLowerCase();

        System.out.println("Day of Christmas after two years is: " + result);
        return result;
    }

    @Step("Entering the day of Christmas after two years in the input field")
    public void enterDay() {
        pageInteractionHelper.enterText(christmasDayInputField, getDayOfChristmasAfterTwoYears());
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "21269";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}