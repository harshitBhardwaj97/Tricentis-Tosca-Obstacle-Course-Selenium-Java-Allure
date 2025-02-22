package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Set;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class PopupWindowsPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By clickMe = By.xpath("//a[.='Click Me']");

    public PopupWindowsPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on the button")
    public void clickOnButton() {
        pageInteractionHelper.clickOnElement(clickMe);
    }

    @Step("Switching to new popup, displaying its information and closing it")
    public void switchToNewPopUpAndCloseIt() {
        Set<String> windowHandles = pageInteractionHelper.getAllWindowHandles();
        String originalWindow = pageInteractionHelper.getWindowHandle();
        String newWindow = null;

        System.out.println("Switching to new window");
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                newWindow = handle;
                break;
            }
        }

        pageInteractionHelper.switchToWindow(newWindow);
        System.out.println("Switched to new window");

        System.out.println("Title and URL of new window are as follows -");
        System.out.println(pageInteractionHelper.getCurrentTitle());
        System.out.println(pageInteractionHelper.getCurrentUrl());

        System.out.println("Closing this window and switching back to original window");
        pageInteractionHelper.closeWindow();

        // Switch back to the original window
        pageInteractionHelper.switchToWindow(originalWindow);
        System.out.println("Title and URL of original window are as follows -");
        System.out.println(pageInteractionHelper.getCurrentTitle());
        System.out.println(pageInteractionHelper.getCurrentUrl());
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "51130";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}