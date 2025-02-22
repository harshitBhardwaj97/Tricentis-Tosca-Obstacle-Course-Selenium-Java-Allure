package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class EmptyPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By generateButton = By.cssSelector("button#generate");
    private final By checkpoints = By.cssSelector("div#checkpoints .checkpoint");

    public EmptyPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Generate button")
    public void clickGenerateButton() {
        pageInteractionHelper.clickOnElement(generateButton);
    }

    @Step("Clicking on all checkpoints")
    public void clickOnCheckpoints() {
        List<WebElement> listOfCheckpoints = pageInteractionHelper.getListOfWebElements(checkpoints);
        for (WebElement checkpoint : listOfCheckpoints) {
            checkpoint.click();
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "66667";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}