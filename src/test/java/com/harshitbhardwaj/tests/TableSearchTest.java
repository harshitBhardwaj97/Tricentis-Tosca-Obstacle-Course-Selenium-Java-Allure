package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TableSearchPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TableSearchTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TableSearchTest.class);

    private TableSearchPage tableSearchPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        tableSearchPage = new TableSearchPage(pageInteractionHelper);
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