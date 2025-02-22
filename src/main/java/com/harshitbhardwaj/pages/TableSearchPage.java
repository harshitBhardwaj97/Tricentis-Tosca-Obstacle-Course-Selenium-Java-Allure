package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TableSearchPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By tableCells = By.xpath("//table[@id='randomTable']//td");
    private final By resultInputField = By.id("resulttext");

    public TableSearchPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Checking if 15 is present in the table")
    private boolean isFifteenPresentInTable() {
        List<WebElement> cells = pageInteractionHelper.getListOfWebElements(tableCells);
        return cells.stream().anyMatch(cell -> cell.getText().equals("15"));
    }

    @Step("Entering the result (true or false) based on presence of 15 in the table")
    public void enterResult() {
        String valueToBeEntered = isFifteenPresentInTable() ? "true" : "false";
        if (valueToBeEntered.equals("true")) {
            System.out.println("15 was found in the table");
        } else {
            System.out.println("15 was not found in the table");
        }
        pageInteractionHelper.enterText(resultInputField, valueToBeEntered);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "41036";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}