package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.BubbleSortPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BubbleSortTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BubbleSortTest.class);

    private BubbleSortPage bubbleSortPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        bubbleSortPage = new BubbleSortPage(pageInteractionHelper);
        bubbleSortPage.navigateToObstacle();
    }

    @Test(timeOut = 45000)
    @Link("https://obstaclecourse.tricentis.com/Obstacles/73589")
    public void testBubbleSort() {
        logger.info("####### testBubbleSort started #######");
        bubbleSortPage.shuffleAndSort();
        Assert.assertTrue(bubbleSortPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testBubbleSort succeeded #######");
    }
}