package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.AgainAndAgainAndAgainPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AgainAndAgainAndAgainTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AgainAndAgainAndAgainTest.class);

    private AgainAndAgainAndAgainPage againAndAgainAndAgainPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        againAndAgainAndAgainPage = new AgainAndAgainAndAgainPage(pageInteractionHelper);
        againAndAgainAndAgainPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/81121")
    public void testAgainAndAgainAndAgain() {
        logger.info("####### testAgainAndAgainAndAgain started #######");
        againAndAgainAndAgainPage.clickOnButtonUntilEnough();
        Assert.assertTrue(againAndAgainAndAgainPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testAgainAndAgainAndAgain succeeded #######");
    }
}