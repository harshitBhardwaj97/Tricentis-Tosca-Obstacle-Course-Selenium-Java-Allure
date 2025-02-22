package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.BeFastAutomatePage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BeFastAutomateTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BeFastAutomateTest.class);

    private BeFastAutomatePage beFastAutomatePage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        beFastAutomatePage = new BeFastAutomatePage(pageInteractionHelper);
        beFastAutomatePage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/87912")
    public void testBeFastAutomate() {
        logger.info("####### testBeFastAutomate started #######");
        beFastAutomatePage.clickOnLoadBooksButton();
        beFastAutomatePage.enterISBN();
        Assert.assertTrue(beFastAutomatePage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testBeFastAutomate succeeded #######");
    }
}