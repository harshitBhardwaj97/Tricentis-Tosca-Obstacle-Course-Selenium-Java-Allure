package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.HalfwayPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HalfwayTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(HalfwayTest.class);

    private HalfwayPage halfwayPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        halfwayPage = new HalfwayPage(pageInteractionHelper);
        halfwayPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/41038")
    public void testHalfway() {
        logger.info("####### testHalfway started #######");
        halfwayPage.clickOnRightHalfOfButton();
        Assert.assertTrue(halfwayPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testHalfway succeeded #######");
    }
}