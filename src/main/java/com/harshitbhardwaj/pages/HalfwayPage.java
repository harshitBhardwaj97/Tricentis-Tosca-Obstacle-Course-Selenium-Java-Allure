package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class HalfwayPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By buttonLocator = By.cssSelector("button#halfButton");

    public HalfwayPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the right half of the button")
    public void clickOnRightHalfOfButton() {
        var button = pageInteractionHelper.getWebElement(buttonLocator);
        var size = button.getSize();
        var height = size.getHeight();
        var width = size.getWidth();

        System.out.println("Button Height: " + height);
        System.out.println("Button Width: " + width);

        int rightHalfX = (width / 2); // Moving just to the right of the midpoint
        int yCoordinate = height / 2; // Vertical center

        System.out.println("clicking at: " + rightHalfX + ", " + yCoordinate);
        pageInteractionHelper.clickOnElementWithOffset(buttonLocator, rightHalfX, yCoordinate);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "41038";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}