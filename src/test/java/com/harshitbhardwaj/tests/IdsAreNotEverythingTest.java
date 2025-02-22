package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.IdsAreNotEverythingPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IdsAreNotEverythingTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(IdsAreNotEverythingTest.class);

    private IdsAreNotEverythingPage idsAreNotEverythingPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        idsAreNotEverythingPage = new IdsAreNotEverythingPage(pageInteractionHelper);
        idsAreNotEverythingPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/22505")
    public void testIdsAreNotEverything() {
        logger.info("####### testIdsAreNotEverything started #######");
        idsAreNotEverythingPage.clickOnClickMeButton();
        Assert.assertTrue(idsAreNotEverythingPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testIdsAreNotEverything succeeded #######");
    }
}