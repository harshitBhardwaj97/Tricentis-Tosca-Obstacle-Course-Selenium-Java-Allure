package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

/**
 * @author Harshit Bhardwaj
 */
public class TwinsPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By rightOneButton = By.xpath("//div[@id='thisoneistheright']//a");

    public TwinsPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the right button")
    public void clickOnRightButton() {
        pageInteractionHelper.clickOnElement(rightOneButton);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "12952";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}