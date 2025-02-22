package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TwoTimesPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TwoTimesTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TwoTimesTest.class);

    private TwoTimesPage twoTimesPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        twoTimesPage = new TwoTimesPage(pageInteractionHelper);
        twoTimesPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/72954")
    public void testTwoTimes() {
        logger.info("####### testTwoTimes started #######");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(twoTimesPage.getTwiceClickButtonText().toLowerCase(), "Click Me 2x".toLowerCase());
        twoTimesPage.clickOnTwiceClickButton();
        softAssert.assertEquals(twoTimesPage.getTwiceClickButtonText().toLowerCase(),
                "Click me once more".toLowerCase());
        twoTimesPage.clickOnTwiceClickButton();
        softAssert.assertAll();
        Assert.assertTrue(twoTimesPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTwoTimes succeeded #######");
    }
}