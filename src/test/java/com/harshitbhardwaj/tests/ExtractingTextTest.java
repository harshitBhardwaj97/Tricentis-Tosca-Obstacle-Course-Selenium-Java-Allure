package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.ExtractingTextPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtractingTextTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ExtractingTextTest.class);

    private ExtractingTextPage extractingTextPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        extractingTextPage = new ExtractingTextPage(pageInteractionHelper);
        extractingTextPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/81012")
    public void testExtractingText() {
        logger.info("####### testExtractingText started #######");
        extractingTextPage.enterAmount();
        Assert.assertTrue(extractingTextPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testExtractingText succeeded #######");
    }
}