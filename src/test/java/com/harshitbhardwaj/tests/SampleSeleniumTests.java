package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseSeleniumTest;
import com.harshitbhardwaj.pages.SampleSeleniumWebsitePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Harshit Bhardwaj
 */
public class SampleSeleniumTests extends BaseSeleniumTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleSeleniumTests.class);

    private SampleSeleniumWebsitePage sampleSeleniumWebsitePage;

    @Override
    @BeforeMethod
    public void setup() {
        super.setup();
        sampleSeleniumWebsitePage = new SampleSeleniumWebsitePage(pageInteractionHelper);
    }

    @Test
    public void checkIfSeleniumHeadingIsDisplayed() {
        logger.info("####### checkIfSeleniumHeadingIsDisplayed started #######");
        Assert.assertTrue(sampleSeleniumWebsitePage.isGettingStartedDisplayed());
        logger.info("####### checkIfSeleniumHeadingIsDisplayed succeeded #######");
    }

    @Test
    public void checkIfSeleniumDocumentationLinkIsWorking() {
        logger.info("####### checkIfSeleniumDocumentationLinkIsWorking started #######");
        Assert.assertTrue(sampleSeleniumWebsitePage.isDocumentationLinkWorking());
        logger.info("####### checkIfSeleniumDocumentationLinkIsWorking succeeded #######");
    }

    @Test
    public void checkIfSeleniumProjectsLinkIsWorking() {
        logger.info("####### checkIfSeleniumProjectsLinkIsWorking started #######");
        Assert.assertTrue(sampleSeleniumWebsitePage.isProjectsLinkWorking());
        logger.info("####### checkIfSeleniumProjectsLinkIsWorking succeeded #######");
    }
}
