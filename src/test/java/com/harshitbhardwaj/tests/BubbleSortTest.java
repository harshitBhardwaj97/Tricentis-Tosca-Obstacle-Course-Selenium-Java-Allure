package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.BubbleSortPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BubbleSortTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BubbleSortTest.class);

    private BubbleSortPage bubbleSortPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        bubbleSortPage = new BubbleSortPage(getPageHelper());
        bubbleSortPage.navigateToObstacle();
    }


    @Test(timeOut = 45000)
    @Link("https://obstaclecourse.tricentis.com/Obstacles/73589")
    public void testBubbleSortComparingList() {
        logger.info("####### testBubbleSortComparingList started #######");
        bubbleSortPage.shuffleAndSortComparingList();
        Assert.assertTrue(bubbleSortPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testBubbtestBubbleSortComparingListleSort succeeded #######");
    }

    @Test(timeOut = 45000)
    @Link("https://obstaclecourse.tricentis.com/Obstacles/73589")
    public void testBubbleSortWithKeepSortingText() {
        logger.info("####### testBubbleSortWithKeepSortingText started #######");
        bubbleSortPage.shuffleAndSortWithKeepSortingText();
        Assert.assertTrue(bubbleSortPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testBubbleSortWithKeepSortingText succeeded #######");
    }
}