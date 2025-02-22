package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class LotsOfRowsPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By tableRows = By.cssSelector("table#rowCountTable tr");
    private final By rowCountInput = By.xpath("//input[@id='rowcount']");
    private final By clickMe = By.xpath("//a[.='Click Me']");

    public LotsOfRowsPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on 'Click Me' button")
    public void clickMe() {
        pageInteractionHelper.clickOnElement(clickMe);
    }

    @Step("Getting the number of rows in the table")
    private int getRowCount() {
        return (int) pageInteractionHelper.getSizeOfElementsList(tableRows);
    }

    @Step("Entering the number of rows")
    public void enterRowCount() {
        pageInteractionHelper.enterText(rowCountInput, String.valueOf(getRowCount()));
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "41032";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}