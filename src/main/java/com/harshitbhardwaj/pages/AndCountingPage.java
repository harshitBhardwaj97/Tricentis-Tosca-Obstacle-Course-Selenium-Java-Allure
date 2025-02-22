package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class AndCountingPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By textLocator = By.id("typeThis");
    private final By textInputField = By.xpath("//select[@id='autocomplete']//following::span//input");
    private final By entryInputField = By.id("entryCount");
    private final By listSize = By
            .xpath("//select[@id='autocomplete']//following::span[@class='select2-results']//ul//li");

    public AndCountingPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Entering text in input field")
    public void enterText() {
        final String text = pageInteractionHelper.getText(textLocator);
        pageInteractionHelper.enterText(textInputField, text);
    }

    @Step("Getting size of the list")
    private long getSizeOfList() {
        return pageInteractionHelper.getSizeOfElementsList(listSize);
    }

    @Step("Entering count in input field")
    public void enterCount() {
        pageInteractionHelper.enterText(entryInputField, String.valueOf(getSizeOfList()));
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "24499";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}