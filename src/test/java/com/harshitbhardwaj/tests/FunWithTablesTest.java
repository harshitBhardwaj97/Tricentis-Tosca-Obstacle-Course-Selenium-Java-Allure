package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.FunWithTablesPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FunWithTablesTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FunWithTablesTest.class);

    private FunWithTablesPage funWithTablesPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        funWithTablesPage = new FunWithTablesPage(pageInteractionHelper);
        funWithTablesPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/92248")
    public void testFunWithTables() {
        logger.info("####### testFunWithTables started #######");
        funWithTablesPage.clickOnJohnDoeEditButton();
        Assert.assertTrue(funWithTablesPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testFunWithTables succeeded #######");
    }
}