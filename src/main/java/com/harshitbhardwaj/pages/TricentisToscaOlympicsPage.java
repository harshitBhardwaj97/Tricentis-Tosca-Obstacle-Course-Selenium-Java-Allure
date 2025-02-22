package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TricentisToscaOlympicsPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By start = By.cssSelector("a#start");
    private final By instructions = By.xpath("//div[@id='text' and @class='instructions']");

    public TricentisToscaOlympicsPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Start")
    public void clickOnStart() {
        pageInteractionHelper.clickOnElement(start);
    }

    @Step("Getting the instructions")
    public String getInstructions() {
        return pageInteractionHelper.getText(instructions);
    }

    @Step("Playing the game")
    public void playGame() {
        boolean isGameOver = false;

        while (!isGameOver) {
            String instructionsText = getInstructions();

            // Move left if the instruction contains "left"
            if (instructionsText.toLowerCase().contains("left")) {
                System.out.println("Moving Left");
                pageInteractionHelper.performKeyAction(Keys.ARROW_LEFT);
            }

            // Move right if the instruction contains "right"
            if (instructionsText.toLowerCase().contains("right")) {
                System.out.println("Moving Right");
                pageInteractionHelper.performKeyAction(Keys.ARROW_RIGHT);
            }

            // End the game in case of a crash or win (Although crash is unlikely)
            if (instructionsText.toLowerCase().contains("you did it")
                    || instructionsText.toLowerCase().contains("crash")) {
                System.out.println("Game Over");
                isGameOver = true;
            }
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "82018";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}