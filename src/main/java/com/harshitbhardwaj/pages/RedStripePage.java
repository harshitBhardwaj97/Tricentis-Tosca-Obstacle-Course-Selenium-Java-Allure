package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class RedStripePage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By generateButton = By.cssSelector("button#generate");
    private final By redStripe = By.
            xpath("//div[contains(@style,'height: 100%; width: 3px; background-color: red;')]");

    public RedStripePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Generate button to get the red stripe")
    public void clickOnGenerateButton() {
        pageInteractionHelper.clickOnElement(generateButton);
    }

    @Step("Clicking on Red Stripe")
    public void clickOnRedStripe() {
        pageInteractionHelper.clickOnElement(redStripe);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "30034";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}