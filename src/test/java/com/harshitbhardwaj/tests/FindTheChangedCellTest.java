package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.FindTheChangedCellPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FindTheChangedCellTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FindTheChangedCellTest.class);

    private FindTheChangedCellPage findTheChangedCellPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        findTheChangedCellPage = new FindTheChangedCellPage(pageInteractionHelper);
        findTheChangedCellPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/73591")
    public void testFindTheChangedCell() {
        logger.info("####### testFindTheChangedCell started #######");
        findTheChangedCellPage.clickOnChangeTable();
        findTheChangedCellPage.enterFetchedValues();
        findTheChangedCellPage.submitSolution();
        Assert.assertTrue(findTheChangedCellPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testFindTheChangedCell succeeded #######");
    }
}