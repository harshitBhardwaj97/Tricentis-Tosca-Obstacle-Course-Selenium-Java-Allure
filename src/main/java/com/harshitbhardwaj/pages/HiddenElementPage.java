package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class HiddenElementPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By hiddenElement = By.cssSelector("span#clickthis");

    public HiddenElementPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on hidden element")
    public void clickOnHiddenElement() {
        pageInteractionHelper.clickOnElement(hiddenElement);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "66666";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}