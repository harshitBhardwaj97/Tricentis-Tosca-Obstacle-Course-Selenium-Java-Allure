package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.HiddenElementPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HiddenElementTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(HiddenElementTest.class);

    private HiddenElementPage hiddenElementPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        hiddenElementPage = new HiddenElementPage(pageInteractionHelper);
        hiddenElementPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/66666")
    public void testHiddenElement() {
        logger.info("####### testHiddenElement started #######");
        hiddenElementPage.clickOnHiddenElement();
        Assert.assertTrue(hiddenElementPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testHiddenElement succeeded #######");
    }
}