package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.AbstractTestHelper;
import com.harshitbhardwaj.support.PageInteractionHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class AdditionPage extends AbstractTestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By numberOne = By.id("no1");
    private final By numberTwo = By.id("no2");
    private final By resultInputField = By.id("result");

    public AdditionPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Performing addition and entering the result")
    public void performAdditionAndEnterTheResult() {
        String numberOneText = pageInteractionHelper.getText(numberOne);
        String numberTwoText = pageInteractionHelper.getText(numberTwo);
        int additionResult = Integer.parseInt(numberOneText) + Integer.parseInt(numberTwoText);
        pageInteractionHelper.enterText(resultInputField, String.valueOf(additionResult));
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "78264";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}