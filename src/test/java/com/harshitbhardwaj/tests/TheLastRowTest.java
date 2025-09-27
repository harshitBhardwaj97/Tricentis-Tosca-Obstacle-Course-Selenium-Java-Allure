package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TheLastRowPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TheLastRowTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TheLastRowTest.class);

    private TheLastRowPage theLastRowPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        theLastRowPage = new TheLastRowPage(getPageHelper());
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