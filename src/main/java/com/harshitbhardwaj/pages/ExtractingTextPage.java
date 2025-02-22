package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class ExtractingTextPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By amount = By.id("alerttext");
    private final By amountInputField = By.id("totalamountText");

    public ExtractingTextPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Entering the amount after extraction")
    public void enterAmount() {
        Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
        var alertText = pageInteractionHelper.getText(amount);
        Matcher m = pattern.matcher(alertText);
        System.out.println(alertText);
        if (m.find()) {
            System.out.println("Match found");
            var amountToBeEntered = m.group().replace("$", "");
            System.out.println("Amount to be entered is: " + amountToBeEntered);
            pageInteractionHelper.enterText(amountInputField, amountToBeEntered);
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "81012";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}