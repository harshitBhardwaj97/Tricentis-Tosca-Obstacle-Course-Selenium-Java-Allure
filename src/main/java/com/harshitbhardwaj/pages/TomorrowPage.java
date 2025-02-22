package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class TomorrowPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By dateFieldInput = By.cssSelector("input#datefield");

    public TomorrowPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Getting tomorrow's date")
    private String getTomorrowsDate() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        System.out.println("Today's date: " + sdf.format(today));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();

        System.out.println("Tomorrow's date: " + sdf.format(tomorrow));
        return sdf.format(tomorrow);
    }

    @Step("Entering tomorrow's date")
    public void enterDate() {
        pageInteractionHelper.enterText(dateFieldInput, getTomorrowsDate());
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "19875";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}