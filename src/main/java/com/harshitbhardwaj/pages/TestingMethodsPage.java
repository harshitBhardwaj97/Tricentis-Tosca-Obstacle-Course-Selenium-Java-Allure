package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TestingMethodsPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final String[] testingMethods = {"Functional", "End2End", "GUI", "Exploratory"};
    private final By testingMethodsMultiSelect = By.id("multiselect");

    public TestingMethodsPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Selecting options")
    public void selectOptions() {
        pageInteractionHelper.multiSelectByVisibleTextContains(testingMethodsMultiSelect, testingMethods);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "94441";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}