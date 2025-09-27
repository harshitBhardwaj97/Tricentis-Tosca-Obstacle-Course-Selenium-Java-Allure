package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.NotATablePage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NotATableTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(NotATableTest.class);

    private NotATablePage notATablePage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        notATablePage = new NotATablePage(getPageHelper());
        notATablePage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/64161")
    public void testNotATable() {
        logger.info("####### testNotATable started #######");
        notATablePage.clickOnGenerateOrderIdButton();
        final String orderId = notATablePage.getOrderId();
        notATablePage.enterOrderId(orderId);
        Assert.assertTrue(notATablePage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testNotATable succeeded #######");
    }
}