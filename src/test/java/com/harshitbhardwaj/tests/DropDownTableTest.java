package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.DropDownTablePage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropDownTableTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DropDownTableTest.class);

    private DropDownTablePage dropDownTablePage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        dropDownTablePage = new DropDownTablePage(pageInteractionHelper);
        dropDownTablePage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/14090")
    public void testDropDownTable() throws InterruptedException {
        logger.info("####### testDropDownTable started #######");
        dropDownTablePage.clickOnGenerateButton();
        dropDownTablePage.iterateListAndSelectRespectiveElements();
        dropDownTablePage.clickOnSubmitButton();
        Assert.assertTrue(dropDownTablePage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testDropDownTable succeeded #######");
    }
}