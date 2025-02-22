package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.MathPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MathTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(MathTest.class);

    private MathPage mathPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        mathPage = new MathPage(pageInteractionHelper);
        mathPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/32403")
    public void testMath() {
        logger.info("####### testMath started #######");
        mathPage.calculateAndEnterResult();
        Assert.assertTrue(mathPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testMath succeeded #######");
    }
}