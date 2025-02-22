package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.PopupWindowsPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PopupWindowsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(PopupWindowsTest.class);

    private PopupWindowsPage popupWindowsPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        popupWindowsPage = new PopupWindowsPage(pageInteractionHelper);
        popupWindowsPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/51130")
    public void testPopupWindows() {
        logger.info("####### testPopupWindows started #######");
        popupWindowsPage.clickOnButton();
        popupWindowsPage.switchToNewPopUpAndCloseIt();
        Assert.assertTrue(popupWindowsPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testPopupWindows succeeded #######");
    }
}