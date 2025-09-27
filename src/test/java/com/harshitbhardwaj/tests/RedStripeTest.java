package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.RedStripePage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RedStripeTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(RedStripeTest.class);

    private RedStripePage redStripePage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        redStripePage = new RedStripePage(getPageHelper());
        redStripePage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/30034")
    public void testRedStripe() {
        logger.info("####### testRedStripe started #######");
        redStripePage.clickOnGenerateButton();
        redStripePage.clickOnRedStripe();
        Assert.assertTrue(redStripePage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testRedStripe succeeded #######");
    }
}