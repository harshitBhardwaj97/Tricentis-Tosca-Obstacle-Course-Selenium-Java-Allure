package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class DropDownTablePage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final String currentRowItemNameTemplate = "//td[@class='task']//parent::tr//td[1]";
    private final String currentRowItemSelectTemplate = "//td[@class='task']//parent::tr//td[2]//select";
    private final By generateButton = By.cssSelector("a#generate");
    private final By submitButton = By.cssSelector("a#submit");

    public DropDownTablePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the generate button")
    public void clickOnGenerateButton() {
        pageInteractionHelper.clickOnElement(generateButton);
    }

    @Step("Extracting the target letter")
    private String extractTargetLetter(String text) {
        return text.split(": ")[1].trim();
    }

    @Step("Iterating over the table and selecting the respective elements")
    public void iterateListAndSelectRespectiveElements() {

        // Iterate over the rows and select the corresponding item based on the first column text
        List<WebElement> itemNames = pageInteractionHelper.getListOfWebElements(By.xpath(currentRowItemNameTemplate));

        for (WebElement itemName : itemNames) {

            // Get the text in the first column
            String itemText = itemName.getText();
            System.out.println("Current Item Text: " + itemText);

            // Extract the first letter from the string, for e.g. "W" from "Select word that starts with letter: W"
            String firstLetter = extractTargetLetter(itemText);
            System.out.println("firstLetter: " + firstLetter);

            // Find the corresponding select dropdown in the second column of the same row
            WebElement selectElement = itemName.findElement(By.xpath("following-sibling::td[1]//select"));

            // Create a Select object to interact with the dropdown
            Select select = new Select(selectElement);
            var currentOptions = select.getOptions();

            // In case any option starts with the target letter, then select that option

            /*
            Imperative Approach:
                for (var option : currentOptions) {
                System.out.println("Option: " + option.getText());
                if (option.getText().startsWith(firstLetter)) {
                    select.selectByVisibleText(option.getText());
                    break;
                }
            }
            */

            select.getOptions().stream()
                    .filter(option -> option.getText().startsWith(firstLetter))
                    .findFirst()
                    .ifPresent(option -> {
                        System.out.println("Selecting option: " + option.getText());
                        select.selectByVisibleText(option.getText());
                    });
        }
    }

    @Step("Clicking on the submit button")
    public void clickOnSubmitButton() {
        pageInteractionHelper.clickOnElement(submitButton);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "14090";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}