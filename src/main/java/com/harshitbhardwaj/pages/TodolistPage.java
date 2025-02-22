package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TodolistPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By draggableRows = By
            .xpath("//table[@id='todo-tasks']//tr[contains(@class,'draggable')]");
    private final By droppedRows = By
            .xpath("//table[@id='completed-tasks']//tbody//tr");

    private final By dropTable = By.xpath("//table[@id='completed-tasks']//tbody");

    public TodolistPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Getting the size of the drag-table")
    public int getDragTableSize() {
        return (int) pageInteractionHelper.getSizeOfElementsList(draggableRows);
    }

    @Step("Getting the size of the drop-table")
    private int getDropTableSize() {
        // Need to maintain a delta of 2, since there are 2 extra rows present initially
        return (int) pageInteractionHelper.getSizeOfElementsList(droppedRows);
    }

    @Step("Dragging and dropping elements")
    public void dragAndDropElements() {
        WebDriverWait wait = new WebDriverWait(pageInteractionHelper.getDriver(), Duration.ofSeconds(10));
        String currentDroppedRowTemplate = "//table[@id='completed-tasks']//td[1][.='%s']//parent::tr";
        String currentRowToBeDraggedTemplate =
                "//table[@id='todo-tasks']//tbody//tr[contains(@class,'draggable')]/td[1][.='%s']//parent::tr";

        int initialDragTableSize = getDragTableSize();
        int initialDropTableSize = getDropTableSize();
        int i = 1;

        System.out.println("Initial Drag Table Size: " + initialDragTableSize);
        System.out.println("Initial Drop Table Size: " + initialDropTableSize);

        while (getDragTableSize() > 0) {
            // Wait until there's at least one draggable element in the drag table
            wait.until(ExpectedConditions.presenceOfElementLocated(draggableRows));

            // Dynamically fetch the first draggable row and the corresponding dropped row
            String elementToBeDragged = String.format(currentRowToBeDraggedTemplate, i);
            String droppedElement = String.format(currentDroppedRowTemplate, i);

            // Perform the drag and drop
            System.out.println("Dragging and dropping: " + elementToBeDragged);
            pageInteractionHelper.dragAndDrop(By.xpath(elementToBeDragged), dropTable);

            // Wait for the drop table to be updated
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(droppedElement)));

            // Check if the drag table size decreased by 1 and drop table size increased by 1
            int currentDragTableSize = getDragTableSize();
            int currentDropTableSize = getDropTableSize();

            // Update the row number
            i++;

            if (currentDragTableSize == initialDragTableSize - 1 && currentDropTableSize == initialDropTableSize + 1) {
                System.out.println("Row moved: Drag Table Size = " + currentDragTableSize +
                        ", Drop Table Size = " + currentDropTableSize);
                initialDragTableSize = currentDragTableSize;  // Update the initial drag table size
                initialDropTableSize = currentDropTableSize;  // Update the initial drop table size
            } else {
                System.out.println("Sizes didn't update as expected. Drag Table Size = " + currentDragTableSize +
                        ", Drop Table Size = " + currentDropTableSize);
            }
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "23292";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}