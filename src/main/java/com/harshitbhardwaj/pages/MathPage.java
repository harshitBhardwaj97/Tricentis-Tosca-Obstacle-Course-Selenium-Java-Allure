package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class MathPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By numberOneLabel = By.cssSelector("label#no1");
    private final By numberTwoLabel = By.cssSelector("label#no2");
    private final By symbol = By.cssSelector("label#symbol1");
    private final By resultInput = By.cssSelector("input#result");

    public MathPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Getting Number 1")
    private int getNumberOne() {
        return Integer.parseInt(pageInteractionHelper.getText(numberOneLabel));
    }

    @Step("Getting Number 2")
    private int getNumberTwo() {
        return Integer.parseInt(pageInteractionHelper.getText(numberTwoLabel));
    }

    @Step("Getting Symbol")
    private String getSymbol() {
        return pageInteractionHelper.getText(symbol);
    }

    @Step("Entering Result")
    private void enterResult(int result) {
        pageInteractionHelper.enterText(resultInput, String.valueOf(result));
    }

    @Step("Calculating and Entering Result")
    public void calculateAndEnterResult() {
        int numberOne = getNumberOne();
        int numberTwo = getNumberTwo();
        String symbol = getSymbol();
        System.out.printf("Number 1: %d and Number 2: %d \n", numberOne, numberTwo);
        int result = switch (symbol) {
            case "+" -> numberOne + numberTwo;
            case "-" -> numberOne - numberTwo;
            case "*" -> numberOne * numberTwo;
            case "/" -> numberOne / numberTwo;
            case "%" -> numberOne % numberTwo;
            default -> throw new IllegalArgumentException("Wrong symbol %s".formatted(symbol));
        };
        System.out.printf("(%d %s %d: %d \n", numberOne, symbol, numberTwo, result);
        enterResult(result);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "32403";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}