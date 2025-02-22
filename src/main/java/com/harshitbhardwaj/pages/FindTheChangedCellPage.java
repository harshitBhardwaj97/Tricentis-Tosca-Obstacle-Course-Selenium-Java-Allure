package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class FindTheChangedCellPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By changeTable = By.cssSelector("a#change");
    private final By submit = By.cssSelector("a#submit");

    private final By rowInput = By.cssSelector("input#rowNumber");
    private final By columnInput = By.cssSelector("input#columnNumber");
    private final By originalValueInput = By.cssSelector("input#originalValue");
    private final By changedValueInput = By.cssSelector("input#changedValue");

    public FindTheChangedCellPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the 'Change' button")
    public void clickOnChangeTable() {
        pageInteractionHelper.clickOnElement(changeTable);
    }

    @Step("Fetching changed cell values")
    private Map<String, Object> fetchChangedCellValues() {

        // Execute the JavaScript code to fetch row, column, original value, and changed value
        JavascriptExecutor jsExecutor = (JavascriptExecutor) pageInteractionHelper.getDriver();
        String script = """
                const row = elementRow;
                const column = elementCol;
                const originalValue = elementOriginal;
                const changedValue = elementChanged;
                return {row, column, originalValue, changedValue};
                """;
        Object result = jsExecutor.executeScript(script);

        // Return the result as a Map
        if (result != null) {
            // Cast the result to a Map
            return (Map<String, Object>) result;
        }
        return null;  // Return null if the result is not available
    }

    @Step("Entering the fetched values into the appropriate fields")
    public void enterFetchedValues() {
        var cellValues = fetchChangedCellValues();
        if (cellValues != null) {
            Long row = (Long) cellValues.get("row");
            Long column = (Long) cellValues.get("column");
            String originalValue = (String) cellValues.get("originalValue");
            String changedValue = (String) cellValues.get("changedValue");

            System.out.println("Row: " + row);
            System.out.println("Column: " + column);
            System.out.println("Original Value: " + originalValue);
            System.out.println("Changed Value: " + changedValue);

            // Enter the values in fields
            pageInteractionHelper.enterText(rowInput, row.toString());
            pageInteractionHelper.enterText(columnInput, column.toString());
            pageInteractionHelper.enterText(originalValueInput, originalValue);
            pageInteractionHelper.enterText(changedValueInput, changedValue);

            // Highlight and move to the changed cell
            highlightChangedCell(row, column);
        } else {
            System.out.println("No changed values found!");
        }
    }

    @Step("Highlighting the changed cell")
    private void highlightChangedCell(Long row, Long column) {
        // JavaScript to highlight the changed cell
        String highlightScript = """
                    const row = arguments[0];
                    const column = arguments[1];
                    const cell = document.getElementById(row + "_" + column);
                    if (cell) {
                        cell.style.backgroundColor = 'yellow';  // Change background color to yellow
                        cell.style.border = '2px solid red';  // Add a red border to the cell
                        cell.scrollIntoView({ behavior: 'smooth', block: 'center' });
                    }
                """;
        System.out.println("Cell highlighted and scrolled into view");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) pageInteractionHelper.getDriver();
        jsExecutor.executeScript(highlightScript, row, column);
    }

    @Step("Submitting the solution")
    public void submitSolution() {
        String submitSolution = """
                check();
                """;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) pageInteractionHelper.getDriver();
        jsExecutor.executeScript(submitSolution);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "73591";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}