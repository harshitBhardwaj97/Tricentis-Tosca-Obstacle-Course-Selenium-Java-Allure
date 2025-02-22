package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.TodolistPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TodolistTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TodolistTest.class);

    private TodolistPage todolistPage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        todolistPage = new TodolistPage(pageInteractionHelper);
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