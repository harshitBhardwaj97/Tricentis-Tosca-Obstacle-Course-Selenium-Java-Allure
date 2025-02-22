package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class ToughCookiePage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By firstNumberInput = By.cssSelector("input#firstNumber");
    private final By secondNumberInput = By.cssSelector("input#secondNumber");
    private final By thirdNumberInput = By.cssSelector("input#thirdNumber");
    private final By generatedText = By.cssSelector("input#generated");

    public ToughCookiePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Entering first number")
    private void enterFirstNumber(String number) {
        pageInteractionHelper.enterText(firstNumberInput, number);
    }

    @Step("Entering second number")
    private void enterSecondNumber(String number) {
        pageInteractionHelper.enterText(secondNumberInput, number);
    }

    @Step("Entering third number")
    private void enterThirdNumber(String number) {
        pageInteractionHelper.enterText(thirdNumberInput, number);
    }

    @Step("Getting the generated text")
    private String getGeneratedText() {
        pageInteractionHelper.clickOnElement(generatedText);
        System.out.println(pageInteractionHelper.getElementValueProperty(generatedText));
        return pageInteractionHelper.getElementValueProperty(generatedText);
    }

    @Step("Extracting numbers from the generated text")
    private List<String> extractNumbers() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(getGeneratedText());

        // Create a list to store the numbers
        List<String> numbers = new ArrayList<>();

        // Find all matches and add them to the list
        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        return numbers;
    }

    @Step("Entering extracted numbers")
    public void enterExtractedNumbers() {
        List<String> numbers = extractNumbers();
        System.out.println("Numbers are: " + numbers);
        enterFirstNumber(numbers.getFirst());
        enterSecondNumber(numbers.get(1));
        enterThirdNumber(numbers.getLast());
        pageInteractionHelper.performKeyAction(Keys.ENTER);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "45618";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}