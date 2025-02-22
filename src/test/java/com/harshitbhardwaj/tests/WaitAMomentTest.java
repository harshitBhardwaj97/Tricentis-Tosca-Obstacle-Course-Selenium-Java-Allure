package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.WaitAMomentPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WaitAMomentTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(WaitAMomentTest.class);

    private WaitAMomentPage waitAMomentPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        waitAMomentPage = new WaitAMomentPage(pageInteractionHelper);
        waitAMomentPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/33678")
    public void testWaitAMoment() {
        logger.info("####### testWaitAMoment started #######");
        waitAMomentPage.clickOnCalculateButton();
        waitAMomentPage.clickOnSendButton();
        Assert.assertTrue(waitAMomentPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testWaitAMoment succeeded #######");
    }
}