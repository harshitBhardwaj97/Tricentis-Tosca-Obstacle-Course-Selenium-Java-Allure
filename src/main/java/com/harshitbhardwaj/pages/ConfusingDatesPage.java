package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class ConfusingDatesPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By generatedDateField = By.cssSelector("input#dateGenerated");
    private final By generateDateButton = By.cssSelector("button#generate");
    private final By dateSolutionInputField = By.cssSelector("input#dateSolution");
    private final By doneButton = By.cssSelector("button#done");

    public ConfusingDatesPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Enter the resulting date")
    public void enterResultingDate() {
        pageInteractionHelper.enterText(dateSolutionInputField, getResultingDate());
    }

    @Step("Clicking on the Done button")
    public void clickOnDoneButton() {
        pageInteractionHelper.clickOnElement(doneButton);
    }

    @Step("Fetching the input date")
    private String fetchDate() {
        pageInteractionHelper.clickOnElement(generateDateButton);
        return pageInteractionHelper.getElementValueProperty(generatedDateField);
    }

    @Step("Getting the resulting/target date")
    private String getResultingDate() {
        var today = new Date();
        Calendar calendar = Calendar.getInstance();
        var usDateFormat = new SimpleDateFormat("M/dd/yyyy");
        var targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fetchedDate = null;
        try {
            fetchedDate = usDateFormat.parse(fetchDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        calendar.setTime(fetchedDate);
        calendar.add(Calendar.MONTH, 2);

        // Set the calendar to the first day of the next month (second following month)
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Get the updated date
        Date resultDate = calendar.getTime();
        String formattedDate = targetDateFormat.format(resultDate);

        // Output the result
        System.out.println("Original Date: " + usDateFormat.format(fetchedDate));
        System.out.println("Calculated Date (ISO format): " + formattedDate);
        return formattedDate;
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "57683";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}