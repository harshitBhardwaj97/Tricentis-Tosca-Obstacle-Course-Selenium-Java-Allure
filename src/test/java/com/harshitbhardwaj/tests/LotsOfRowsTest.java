package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.LotsOfRowsPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LotsOfRowsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LotsOfRowsTest.class);

    private LotsOfRowsPage lotsOfRowsPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        lotsOfRowsPage = new LotsOfRowsPage(pageInteractionHelper);
        lotsOfRowsPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/41032")
    public void testLotsOfRows() {
        logger.info("####### testLotsOfRows started #######");
        lotsOfRowsPage.enterRowCount();
        lotsOfRowsPage.clickMe();
        Assert.assertTrue(lotsOfRowsPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testLotsOfRows succeeded #######");
    }
}