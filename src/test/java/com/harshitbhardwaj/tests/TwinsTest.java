package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TwinsPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TwinsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TwinsTest.class);

    private TwinsPage twinsPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        twinsPage = new TwinsPage(pageInteractionHelper);
        twinsPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/12952")
    public void testTwins() {
        logger.info("####### testTwins started #######");
        twinsPage.clickOnRightButton();
        Assert.assertTrue(twinsPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTwins succeeded #######");
    }
}