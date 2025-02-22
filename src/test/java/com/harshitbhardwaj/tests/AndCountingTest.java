package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.AndCountingPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndCountingTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AndCountingTest.class);

    private AndCountingPage andCountingPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        andCountingPage = new AndCountingPage(pageInteractionHelper);
        andCountingPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/24499")
    public void testAndCounting() {
        logger.info("####### testAndCounting started #######");
        andCountingPage.enterText();
        andCountingPage.enterCount();
        Assert.assertTrue(andCountingPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testAndCounting succeeded #######");
    }
}