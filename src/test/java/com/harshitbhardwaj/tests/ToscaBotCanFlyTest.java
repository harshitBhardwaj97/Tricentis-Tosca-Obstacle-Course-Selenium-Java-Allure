package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.ToscaBotCanFlyPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToscaBotCanFlyTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToscaBotCanFlyTest.class);

    private ToscaBotCanFlyPage toscabotCanFlyPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        toscabotCanFlyPage = new ToscaBotCanFlyPage(pageInteractionHelper);
        toscabotCanFlyPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/60469")
    public void testToscaBotCanFly() throws InterruptedException {
        logger.info("####### testToscaBotCanFly started #######");
        toscabotCanFlyPage.performDragAndDrop();
        Assert.assertTrue(toscabotCanFlyPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testToscaBotCanFly succeeded #######");
    }
}