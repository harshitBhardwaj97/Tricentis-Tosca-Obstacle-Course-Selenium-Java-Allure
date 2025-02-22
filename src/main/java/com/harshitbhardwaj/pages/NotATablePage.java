package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class NotATablePage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By generateOrderIdButton = By.xpath("//a[@id='generate']");
    private final By offerIDInput = By.cssSelector("input#offerId");
    private final By orderIdNameCell = By
            .xpath("//div[contains(@class,'propertyGrid')]/div[.='order id']");
    private final By orderIdCell = By.
            xpath("//div[contains(@class,'propertyGrid')]/div[.='order id']//following-sibling::div");

    public NotATablePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on generate order id button")
    public void clickOnGenerateOrderIdButton() {
        pageInteractionHelper.clickOnElement(generateOrderIdButton);
    }

    @Step("Getting order id")
    public String getOrderId() {
        return pageInteractionHelper.getText(orderIdCell);
    }

    @Step("Entering order id: {orderId}")
    public void enterOrderId(String orderId) {
        System.out.println("Order ID: " + orderId);
        pageInteractionHelper.enterText(offerIDInput, orderId);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "64161";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}