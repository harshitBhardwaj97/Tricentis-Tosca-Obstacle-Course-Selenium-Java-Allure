package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TheLastRowPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By lastRow = By.xpath("(//table[@id='orderTable']//tr/td)[last()]");
    private final By inputField = By.id("ordervalue");

    public TheLastRowPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Entering the value of the last row in the input field")
    public void enterLastRowValue() {
        String lastRowValue = pageInteractionHelper.getText(lastRow);
        pageInteractionHelper.enterText(inputField, lastRowValue);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "70310";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}