package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.EmptyPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmptyTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(EmptyTest.class);

    private EmptyPage emptyPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        emptyPage = new EmptyPage(pageInteractionHelper);
        emptyPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/66667")
    public void testEmpty() {
        logger.info("####### testEmpty started #######");
        emptyPage.clickGenerateButton();
        emptyPage.clickOnCheckpoints();
        Assert.assertTrue(emptyPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testEmpty succeeded #######");
    }
}