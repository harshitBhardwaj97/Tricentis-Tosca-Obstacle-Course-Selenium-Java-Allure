package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.FutureChristmasPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FutureChristmasTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FutureChristmasTest.class);

    private FutureChristmasPage futureChristmasPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        futureChristmasPage = new FutureChristmasPage(pageInteractionHelper);
        futureChristmasPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/21269")
    public void testFutureChristmas() {
        logger.info("####### testFutureChristmas started #######");
        futureChristmasPage.enterDay();
        Assert.assertTrue(futureChristmasPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testFutureChristmas succeeded #######");
    }
}