package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class ScrollIntoViewPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By inputField = By.cssSelector("input#textfield");
    private final By submitButton = By.cssSelector("a#submit");

    public ScrollIntoViewPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Scrolling the input field into view")
    public void scrollInputIntoView() {
        System.out.println("Input element is located inside an iFrame, hence first switching to the iFrame");
        pageInteractionHelper.switchToIframe("container");
        System.out.println("Now scrolling the input field into view");
        pageInteractionHelper.scrollElementIntoView(inputField);
    }

    @Step("Entering the input")
    public void enterInput() {
        String input = "Tosca";
        pageInteractionHelper.enterText(inputField, input);
    }

    @Step("Clicking on the submit button")
    public void clickOnSubmit() {
        System.out.println("Switching back to the default content");
        pageInteractionHelper.switchToDefaultContent();
        pageInteractionHelper.scrollElementIntoView(submitButton);
        pageInteractionHelper.clickOnElementUsingJavascript(submitButton);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "99999";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}