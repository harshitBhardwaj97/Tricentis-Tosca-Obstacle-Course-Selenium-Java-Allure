package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.EscapePage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EscapeTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(EscapeTest.class);

    private EscapePage escapePage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        escapePage = new EscapePage(pageInteractionHelper);
        escapePage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/41041")
    public void testEscape() {
        logger.info("####### testEscape started #######");
        escapePage.enterInput();
        Assert.assertTrue(escapePage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testEscape succeeded #######");
    }
}