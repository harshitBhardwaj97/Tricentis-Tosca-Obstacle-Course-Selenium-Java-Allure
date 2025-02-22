package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class ClickMeIfYouCanPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By buttonToClick = By.id("buttontoclick");

    public ClickMeIfYouCanPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the button using JavaScriptExecutor")
    public void clickOnButton() {
        pageInteractionHelper.clickOnElementUsingJavascript(buttonToClick);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "41040";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}