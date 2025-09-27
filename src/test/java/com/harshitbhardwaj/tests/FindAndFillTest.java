package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.FindAndFillPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FindAndFillTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FindAndFillTest.class);

    private FindAndFillPage findAndFillPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        findAndFillPage = new FindAndFillPage(getPageHelper());
        findAndFillPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/73590")
    public void testFindAndFill() {
        logger.info("####### testFindAndFill started #######");
        findAndFillPage.enterPassword();
        findAndFillPage.clickOnClickMe();
        Assert.assertTrue(findAndFillPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testFindAndFill succeeded #######");
    }
}