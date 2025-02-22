package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.ErrorsOccurPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ErrorsOccurTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ErrorsOccurTest.class);

    private ErrorsOccurPage errorsOccurPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        errorsOccurPage = new ErrorsOccurPage(pageInteractionHelper);
        errorsOccurPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/70924")
    public void testErrorsOccur() {
        logger.info("####### testErrorsOccur started #######");
        errorsOccurPage.performErrorOccursTest();
        Assert.assertTrue(errorsOccurPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testErrorsOccur succeeded #######");
    }
}