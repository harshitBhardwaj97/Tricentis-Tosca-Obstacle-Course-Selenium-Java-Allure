package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class FunWithTablesPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By johnDoeEditButton =
            By.xpath("//tr[td[1] = 'John' and td[2] = 'Doe']//div/button[.='Edit']");

    public FunWithTablesPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on John Doe Edit button")
    public void clickOnJohnDoeEditButton() {
        pageInteractionHelper.clickOnElement(johnDoeEditButton);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "92248";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}