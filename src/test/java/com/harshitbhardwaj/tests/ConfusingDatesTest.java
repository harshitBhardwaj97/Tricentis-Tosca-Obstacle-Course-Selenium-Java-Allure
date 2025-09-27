package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.ConfusingDatesPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ConfusingDatesTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ConfusingDatesTest.class);

    private ConfusingDatesPage confusingDatesPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        confusingDatesPage = new ConfusingDatesPage(getPageHelper());
        confusingDatesPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/57683")
    public void testConfusingDates() {
        logger.info("####### testConfusingDates started #######");
        confusingDatesPage.enterResultingDate();
        confusingDatesPage.clickOnDoneButton();
        Assert.assertTrue(confusingDatesPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testConfusingDates succeeded #######");
    }
}