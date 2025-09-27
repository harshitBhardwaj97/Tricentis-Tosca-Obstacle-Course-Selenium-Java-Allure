package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseSeleniumTest;
import com.harshitbhardwaj.pages.SampleSeleniumWebsitePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.harshitbhardwaj.constants.Constants.Common.SELENIUM_WEBSITE_URL;

/**
 * @author Harshit Bhardwaj
 */
public class SampleSeleniumTests extends BaseSeleniumTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleSeleniumTests.class);

    @Override
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) {
        super.setup(browser);
        getPageHelper().navigateTo(SELENIUM_WEBSITE_URL);
    }

    @Test
    public void checkIfSeleniumHeadingIsDisplayed() {
        logger.info("####### checkIfSeleniumHeadingIsDisplayed started #######");
        SampleSeleniumWebsitePage sampleSeleniumWebsitePage = new SampleSeleniumWebsitePage(getPageHelper());
        Assert.assertTrue(sampleSeleniumWebsitePage.isGettingStartedDisplayed());
        logger.info("####### checkIfSeleniumHeadingIsDisplayed succeeded #######");
    }

    @Test
    public void checkIfSeleniumDocumentationLinkIsWorking() {
        logger.info("####### checkIfSeleniumDocumentationLinkIsWorking started #######");
        SampleSeleniumWebsitePage sampleSeleniumWebsitePage = new SampleSeleniumWebsitePage(getPageHelper());
        Assert.assertTrue(sampleSeleniumWebsitePage.isDocumentationLinkWorking());
        logger.info("####### checkIfSeleniumDocumentationLinkIsWorking succeeded #######");
    }

    @Test
    public void checkIfSeleniumProjectsLinkIsWorking() {
        logger.info("####### checkIfSeleniumProjectsLinkIsWorking started #######");
        SampleSeleniumWebsitePage sampleSeleniumWebsitePage = new SampleSeleniumWebsitePage(getPageHelper());
        Assert.assertTrue(sampleSeleniumWebsitePage.isProjectsLinkWorking());
        logger.info("####### checkIfSeleniumProjectsLinkIsWorking succeeded #######");
    }
}
