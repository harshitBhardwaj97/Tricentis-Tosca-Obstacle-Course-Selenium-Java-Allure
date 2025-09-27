package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TableSearchPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TableSearchTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TableSearchTest.class);

    private TableSearchPage tableSearchPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        tableSearchPage = new TableSearchPage(getPageHelper());
        tableSearchPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/41036")
    public void testTableSearch() {
        logger.info("####### testTableSearch started #######");
        tableSearchPage.enterResult();
        Assert.assertTrue(tableSearchPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTableSearch succeeded #######");
    }
}