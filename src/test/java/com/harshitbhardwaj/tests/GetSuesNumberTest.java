package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.GetSuesNumberPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetSuesNumberTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GetSuesNumberTest.class);

    private GetSuesNumberPage getSuesNumberPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        getSuesNumberPage = new GetSuesNumberPage(getPageHelper());
        getSuesNumberPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/72946")
    public void testGetSuesNumber() {
        logger.info("####### testGetSuesNumber started #######");
        getSuesNumberPage.clickOnDownloadLink();
        getSuesNumberPage.enterSuesNumber();
        Assert.assertTrue(getSuesNumberPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testGetSuesNumber succeeded #######");
    }
}