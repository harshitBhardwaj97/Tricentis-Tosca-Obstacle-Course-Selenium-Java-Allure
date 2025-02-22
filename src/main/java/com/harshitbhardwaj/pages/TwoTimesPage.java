package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TwoTimesPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By twiceClickButton = By.xpath("//a[contains(@id,'rnd') and contains(.,'Click')]");

    public TwoTimesPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the 'Click' button")
    public void clickOnTwiceClickButton() {
        pageInteractionHelper.clickOnElement(twiceClickButton);
    }

    @Step("Getting the text of the 'Click' button")
    public String getTwiceClickButtonText() {
        return pageInteractionHelper.getText(twiceClickButton);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "72954";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}