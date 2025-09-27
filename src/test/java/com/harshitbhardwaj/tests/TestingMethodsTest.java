package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TestingMethodsPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestingMethodsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TestingMethodsTest.class);

    private TestingMethodsPage testingMethodsPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        testingMethodsPage = new TestingMethodsPage(getPageHelper());
        testingMethodsPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/94441")
    public void testTestingMethods() {
        logger.info("####### testTestingMethods started #######");
        testingMethodsPage.selectOptions();
        Assert.assertTrue(testingMethodsPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTestingMethods succeeded #######");
    }
}