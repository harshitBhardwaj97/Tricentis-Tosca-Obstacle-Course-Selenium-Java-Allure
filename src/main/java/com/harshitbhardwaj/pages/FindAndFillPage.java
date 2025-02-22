package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class FindAndFillPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By clickMe = By.xpath("//a[contains(.,'Click Me')]");
    private final By passwordInputField = By.xpath("//input[@id='pass']");
    private final By hiddenPasswordInputField = By.xpath("//input[@id='actual']");

    public FindAndFillPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Click Me")
    public void clickOnClickMe() {
        pageInteractionHelper.clickOnElement(clickMe);
    }

    @Step("Entering password in password input field")
    public void enterPassword() {
        String validPassword = "ABC";
        pageInteractionHelper.enterText(passwordInputField, validPassword);

        // Here the actual password field is hidden with style: display=none;
        // So we need to make it visible first and then enter the
        // value in it, or else we will get ElementNotInteractableException
        System.out.println("Changing the style.display of hidden password field from none to block");
        String makeElementVisibleScript = "arguments[0].style.display='block';";
        WebElement hiddenPasswordElement = pageInteractionHelper.getWebElement(hiddenPasswordInputField);
        ((JavascriptExecutor) pageInteractionHelper.getDriver()).executeScript(makeElementVisibleScript,
                hiddenPasswordElement);
        pageInteractionHelper.enterText(hiddenPasswordInputField, validPassword);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "73590";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}