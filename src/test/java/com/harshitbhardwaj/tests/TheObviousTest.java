package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TheObviousPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TheObviousTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TheObviousTest.class);

    private TheObviousPage theObviousPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        theObviousPage = new TheObviousPage(pageInteractionHelper);
        theObviousPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/73588")
    public void testTheObvious() {
        logger.info("####### testTheObvious started #######");
        theObviousPage.clickOnGenerateRandomText();
        theObviousPage.selectText();
        theObviousPage.clickOnSubmit();
        Assert.assertTrue(theObviousPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTheObvious succeeded #######");
    }
}