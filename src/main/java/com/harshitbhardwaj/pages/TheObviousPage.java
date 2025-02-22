package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TheObviousPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By generateRandomTextButton = By.cssSelector("a#clickme");
    private final By inputRandomText = By.cssSelector("input#randomtext");
    private final By selectLink = By.id("selectlink");
    private final By submitButton = By.cssSelector("a#submitanswer");

    public TheObviousPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Generate Random Text")
    public void clickOnGenerateRandomText() {
        pageInteractionHelper.clickOnElement(generateRandomTextButton);
    }

    @Step("Getting the text value")
    private String getTextValue() {
        // Use getElementValueProperty() instead of getText(), because its an input field
        return pageInteractionHelper.getElementValueProperty(inputRandomText);
    }

    @Step("Selecting the text based on random text value")
    public void selectText() {
        pageInteractionHelper.selectByVisibleTextContains(selectLink, getTextValue());
    }

    @Step("Clicking on Submit")
    public void clickOnSubmit() {
        pageInteractionHelper.clickOnElement(submitButton);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "73588";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}