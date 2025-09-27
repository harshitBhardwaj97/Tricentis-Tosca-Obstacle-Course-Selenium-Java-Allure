package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TodolistPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TodolistTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TodolistTest.class);

    private TodolistPage todolistPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        todolistPage = new TodolistPage(getPageHelper());
        todolistPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/23292")
    public void testTodolist() {
        logger.info("####### testTodolist started #######");
        todolistPage.dragAndDropElements();
        Assert.assertTrue(todolistPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testTodolist succeeded #######");
    }
}