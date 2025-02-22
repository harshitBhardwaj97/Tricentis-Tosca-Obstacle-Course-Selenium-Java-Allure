package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class MeetingSchedulerPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By thursdaysStatus = By.xpath("//table//td[.='11:00 - 13:00']/parent::tr/td[5]");
    private final By resultInput = By.cssSelector("input#resulttext");

    public MeetingSchedulerPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Getting the status of Thursday")
    private String getThursdaysStatus() {
        return pageInteractionHelper.getText(thursdaysStatus);
    }

    @Step("Entering the status of Thursday in the result input")
    public void enterStatus() {
        String result = getThursdaysStatus();
        pageInteractionHelper.enterText(resultInput, result);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "41037";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}