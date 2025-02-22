package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TomorrowPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TomorrowTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TomorrowTest.class);

    private TomorrowPage tomorrowPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        tomorrowPage = new TomorrowPage(pageInteractionHelper);
        tomorrowPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/19875")
    public void testTomorrow() {
        logger.info("####### testTomorrow started #######");
        tomorrowPage.enterDate();
        Assert.assertTrue(tomorrowPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTomorrow succeeded #######");
    }
}