package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.ScrollIntoViewPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScrollIntoViewTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ScrollIntoViewTest.class);

    private ScrollIntoViewPage scrollIntoViewPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        scrollIntoViewPage = new ScrollIntoViewPage(pageInteractionHelper);
        scrollIntoViewPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/99999")
    public void testScrollIntoView() {
        logger.info("####### testScrollIntoView started #######");
        scrollIntoViewPage.scrollInputIntoView();
        scrollIntoViewPage.enterInput();
        scrollIntoViewPage.clickOnSubmit();
        Assert.assertTrue(scrollIntoViewPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testScrollIntoView succeeded #######");
    }
}