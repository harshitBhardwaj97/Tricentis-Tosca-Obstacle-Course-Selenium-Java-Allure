package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.pages.MeetingSchedulerPage;
import io.qameta.allure.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MeetingSchedulerTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(MeetingSchedulerTest.class);

    private MeetingSchedulerPage meetingSchedulerPage;

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        meetingSchedulerPage = new MeetingSchedulerPage(getPageHelper());
        meetingSchedulerPage.navigateToObstacle();
    }

    @Test
    @Link("https://obstaclecourse.tricentis.com/Obstacles/41037")
    public void testMeetingScheduler() {
        logger.info("####### testMeetingScheduler started #######");
        meetingSchedulerPage.enterStatus();
        Assert.assertTrue(meetingSchedulerPage.isTestPassed(), "Success Heading was not shown after performing the actions");
        logger.info("####### testMeetingScheduler succeeded #######");
    }
}