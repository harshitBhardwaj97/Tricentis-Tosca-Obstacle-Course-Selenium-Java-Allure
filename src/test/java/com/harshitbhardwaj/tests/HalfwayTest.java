package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.HalfwayPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HalfwayTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(HalfwayTest.class);

    private HalfwayPage halfwayPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        halfwayPage = new HalfwayPage(getPageHelper());
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