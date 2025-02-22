package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class ErrorsOccurPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By callTechnician = By.id("tech");
    private final By countOrErrorButton = By
            .xpath("//button[@id='tech']//preceding-sibling::button");

    public ErrorsOccurPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Count Button")
    private void clickOnCountButton() {
        pageInteractionHelper.clickOnElement(countOrErrorButton);
    }

    @Step("Checking if Error has occurred")
    private boolean checkIfErrorHasOccurred() {
        return checkCountOrErrorText().equals("ERROR");
    }

    @Step("Checking the text of Count Button")
    private String checkCountOrErrorText() {
        return pageInteractionHelper.getText(countOrErrorButton);
    }

    @Step("Clicking on Call Technician Button")
    private void clickOnCallTechnicianButton() {
        pageInteractionHelper.clickOnElement(callTechnician);
    }

    @Step("Performing the test")
    public void performErrorOccursTest() {
        do {
            clickOnCountButton();

            // Recheck on each iteration if "ERROR" is displayed
            boolean hasErrorOccurred = checkIfErrorHasOccurred();

            // If "ERROR" is displayed, then click on "Call Technician" button
            if (hasErrorOccurred) {
                clickOnCallTechnicianButton();
            }

            // Repeat the process until the count reaches 10
        } while (Integer.parseInt(checkCountOrErrorText()) != 10);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "70924";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}