package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class WaitAMomentPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By calculateButton = By.xpath("//button[@id='one']");
    private final By sendButton = By.xpath("//button[@id='two']");
    private final By progressBar = By.xpath("//div[@id='myBar']");

    public WaitAMomentPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Calculate Button")
    public void clickOnCalculateButton() {
        pageInteractionHelper.clickOnElement(calculateButton);
    }

    @Step("Waiting for Send Button to be enabled")
    private boolean waitForSendButtonConditions() {
        WebDriverWait wait = new WebDriverWait(pageInteractionHelper.getDriver(), Duration.ofSeconds(25));
        return wait.until(d -> {
            var currentProgress = pageInteractionHelper.getText(progressBar);
            System.out.println("Current Progress: " + currentProgress);
            var isSendEnabled = pageInteractionHelper.isElementEnabled(sendButton);
            System.out.println("Is Send Button Enabled: " + isSendEnabled);
            System.out.println("==========================================");
            // Wait until button is enabled
            return isSendEnabled;
        });
    }

    @Step("Clicking on the Send Button after it's enabled")
    public void clickOnSendButton() {
        if (waitForSendButtonConditions()) {
            pageInteractionHelper.clickOnElement(sendButton);
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "33678";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}