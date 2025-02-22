package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TheLastRowPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TheLastRowTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TheLastRowTest.class);

    private TheLastRowPage theLastRowPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        theLastRowPage = new TheLastRowPage(pageInteractionHelper);
        theLastRowPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/70310")
    public void testTheLastRow() {
        logger.info("####### testTheLastRow started #######");
        theLastRowPage.enterLastRowValue();
        Assert.assertTrue(theLastRowPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTheLastRow succeeded #######");
    }
}