package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class ToscaBotCanFlyPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By source = By.id("from");
    private final By target = By.id("to");

    public ToscaBotCanFlyPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Performing Drag and Drop")
    public void performDragAndDrop() {
        pageInteractionHelper.dragAndDrop(source, target);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "60469";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}