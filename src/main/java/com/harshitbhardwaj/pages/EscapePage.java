package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class EscapePage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By resultInput = By.cssSelector("input#resulttext");

    public EscapePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Entering the input {Click} in the input field")
    public void enterInput() {
        String input = "{Click}";
        pageInteractionHelper.enterText(resultInput, input);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "41041";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}