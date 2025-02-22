package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * @author Harshit Bhardwaj
 */
public class SampleSeleniumWebsitePage {

    private final PageInteractionHelper pageInteractionHelper;

    private final By gettingStartedHeading = By.xpath("//h2[.='Getting Started']");
    private final By documentationLink = By.xpath("//a[@href='/documentation']");
    private final By documentationPageHeading =
            By.xpath("//h1[.='The Selenium Browser Automation Project']");

    public SampleSeleniumWebsitePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Check if the 'Getting Started' heading is displayed")
    public boolean isGettingStartedDisplayed() {
        return pageInteractionHelper.isElementDisplayed(gettingStartedHeading);
    }

    @Step("Click on the 'Documentation' link")
    private void clickOnDocumentationLink() {
        pageInteractionHelper.clickOnElement(documentationLink);
    }

    @Step("Check if the documentation page is displayed")
    public boolean isDocumentationLinkWorking() {
        clickOnDocumentationLink();
        return pageInteractionHelper.isElementDisplayed(documentationPageHeading) &&
                pageInteractionHelper.getCurrentUrl().contains("/documentation");
    }
}