package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

/**
 * @author Harshit Bhardwaj
 */
public class IdsAreNotEverythingPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By clickMe = By.xpath("//a[.='Click me!']");

    public IdsAreNotEverythingPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the 'Click me!' button")
    public void clickOnClickMeButton() {
        pageInteractionHelper.clickOnElement(clickMe);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "22505";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}
