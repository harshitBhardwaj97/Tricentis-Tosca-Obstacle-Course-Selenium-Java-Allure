package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.ToughCookiePage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToughCookieTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToughCookieTest.class);

    private ToughCookiePage toughCookiePage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        toughCookiePage = new ToughCookiePage(pageInteractionHelper);
        toughCookiePage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/45618")
    public void testToughCookie() {
        logger.info("####### testToughCookie started #######");
        toughCookiePage.enterExtractedNumbers();
        Assert.assertTrue(toughCookiePage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testToughCookie succeeded #######");
    }
}