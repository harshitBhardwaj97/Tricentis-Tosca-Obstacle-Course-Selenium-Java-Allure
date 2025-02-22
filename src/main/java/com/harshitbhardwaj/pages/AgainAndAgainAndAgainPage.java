package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class AgainAndAgainAndAgainPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By clickButton = By.cssSelector("a#button");

    public AgainAndAgainAndAgainPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Getting the text of the button")
    private String getButtonText() {
        return pageInteractionHelper.getText(clickButton);
    }

    @Step("Clicking on the button until the text is 'Enough'")
    public void clickOnButtonUntilEnough() {
        String buttonText = getButtonText(); // Get the button text once before the loop starts
        var isSuccessMessageDisplayed = false;
        while (!buttonText.equals("Enough") && !isSuccessMessageDisplayed) {
            System.out.println("Current button text: " + buttonText);
            // Click the button
            pageInteractionHelper.clickOnElement(clickButton);

            // After clicking, get the updated text
            buttonText = getButtonText();

            // Also check for success message before clicking button again, to avoid ElementClickInterceptedException.
            // Here I'm not using pageInteractionHelper.isElementDisplayed(), because it uses explicit wait
            // and waits for 5 seconds before returning false.
            try {
                isSuccessMessageDisplayed = pageInteractionHelper.getDriver().findElement(goodJobHeading).isDisplayed();
            } catch (NoSuchElementException | ElementClickInterceptedException e) {
                System.out.println("Tried finding the success heading (without using explicit wait), hence failed");
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "81121";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}