package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.AdditionPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AdditionTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AdditionTest.class);

    private AdditionPage additionPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        additionPage = new AdditionPage(getPageHelper());
        additionPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/78264")
    public void testAddition() {
        logger.info("####### testAddition started #######");
        additionPage.performAdditionAndEnterTheResult();
        Assert.assertTrue(additionPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testAddition succeeded #######");
    }
}