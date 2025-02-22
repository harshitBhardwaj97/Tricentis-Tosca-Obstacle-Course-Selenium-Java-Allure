package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class BubbleSortPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By numberElements = By.cssSelector("#array .num");
    private final By firstNumber = By.xpath("//div[@class='bubble']/div[1]");
    private final By secondNumber = By.xpath("//div[@class='bubble']/div[2]");
    private final By nextButton = By.xpath("//button[.='Next']");
    private final By swapButton = By.xpath("//button[.='Swap']");

    public BubbleSortPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    public List<WebElement> getNumberElements() {
        return pageInteractionHelper.getListOfWebElements(numberElements);
    }

    public int getFirstNumber() {
        return Integer.parseInt(pageInteractionHelper.getText(firstNumber));
    }

    public int getSecondNumber() {
        return Integer.parseInt(pageInteractionHelper.getText(secondNumber));
    }

    @Step("Clicking on the Swap button")
    public void clickSwapButton() {
        pageInteractionHelper.clickOnElement(swapButton);
    }

    @Step("Clicking on the Next button")
    public void clickNextButton() {
        pageInteractionHelper.clickOnElement(nextButton);
    }

    public boolean isListSortedInAscendingOrder(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    // Method to shuffle the numbers until the list is sorted
    @Step("Shuffling the numbers until the list is sorted")
    public void shuffleAndSort() {
        List<WebElement> numbers;
        List<Integer> list;

        // Get initial list of numbers
        numbers = getNumberElements();
        list = numbers.stream().map(e -> Integer.parseInt(e.getText())).collect(Collectors.toList());

        System.out.println("Initial List: " + list);
        boolean isSorted = isListSortedInAscendingOrder(list);

        if (isSorted) {
            System.out.println("List is already sorted in ascending order.");
            return;
        }

        WebDriverWait wait = new WebDriverWait(pageInteractionHelper.getDriver(), Duration.ofSeconds(10));

        // Shuffle (Bubble Sort) logic
        while (!isSorted) {
            try {
                // Handle the exception here
                int firstNum = getFirstNumber();
                int secondNum = getSecondNumber();

                // If first number is greater than second, swap them
                if (firstNum > secondNum) {
                    System.out.println("Swapping " + firstNum + " and " + secondNum);
                    clickSwapButton();
                    clickNextButton();
                } else {
                    System.out.println("No swap is required");
                    clickNextButton();
                }

                // Re-check if the list is sorted after each action
                numbers = getNumberElements();
                list = numbers.stream().map(e -> Integer.parseInt(e.getText())).collect(Collectors.toList());
                isSorted = isListSortedInAscendingOrder(list);

            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException");
                continue;
            }
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "73589";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}