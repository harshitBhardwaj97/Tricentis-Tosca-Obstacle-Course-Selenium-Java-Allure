package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TestdataInAServicePage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestdataInAServiceTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TestdataInAServiceTest.class);

    private TestdataInAServicePage testdataInAServicePage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        testdataInAServicePage = new TestdataInAServicePage(pageInteractionHelper);
        testdataInAServicePage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/16384")
    public void testTestdataInAService() {
        logger.info("####### testTestdataInAService started #######");
        testdataInAServicePage.clickOnCreateTDSButton();
        testdataInAServicePage.enter();
        testdataInAServicePage.clickOnSubmit();
        Assert.assertTrue(testdataInAServicePage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTestdataInAService succeeded #######");
    }
}