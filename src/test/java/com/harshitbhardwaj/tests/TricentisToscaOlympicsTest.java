package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TricentisToscaOlympicsPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TricentisToscaOlympicsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TricentisToscaOlympicsTest.class);

    private TricentisToscaOlympicsPage tricentisToscaOlympicsPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        tricentisToscaOlympicsPage = new TricentisToscaOlympicsPage(getPageHelper());
        tricentisToscaOlympicsPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/82018")
    public void testTricentisToscaOlympics() {
        logger.info("####### testTricentisToscaOlympics started #######");
        tricentisToscaOlympicsPage.clickOnStart();
        tricentisToscaOlympicsPage.playGame();
        Assert.assertTrue(tricentisToscaOlympicsPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTricentisToscaOlympics succeeded #######");
    }
}