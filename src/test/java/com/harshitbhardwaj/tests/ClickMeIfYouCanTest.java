package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.ClickMeIfYouCanPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClickMeIfYouCanTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ClickMeIfYouCanTest.class);

    private ClickMeIfYouCanPage clickMeIfYouCanPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        clickMeIfYouCanPage = new ClickMeIfYouCanPage(getPageHelper());
        clickMeIfYouCanPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/41040")
    public void testClickMeIfYouCan() {
        logger.info("####### testClickMeIfYouCan started #######");
        clickMeIfYouCanPage.clickOnButton();
        Assert.assertTrue(clickMeIfYouCanPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testClickMeIfYouCan succeeded #######");
    }
}