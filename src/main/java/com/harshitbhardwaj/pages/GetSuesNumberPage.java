package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import com.harshitbhardwaj.utils.FileOrDirectoryCreator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;
import static com.harshitbhardwaj.constants.Constants.Common.DOWNLOADS_DIRECTORY;

public class GetSuesNumberPage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By downloadLink = By.xpath("//p[contains(.,'Catalog')]//a");
    private final By suesNumberInputField = By.cssSelector("input#NumberSue");
    private final String downloadedFileName = "Catalog.xml";

    public GetSuesNumberPage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;

        // This test requires downloading a file, so we create a downloads directory
        // and clean it before starting the test
        FileOrDirectoryCreator.DownloadsDirectoryCreator.createDownloadsDirectory();
        FileOrDirectoryCreator.DownloadsDirectoryCreator.cleanDownloadDirectory();
    }

    @Step("Clicking on the download link")
    public void clickOnDownloadLink() {
        pageInteractionHelper.clickOnElement(downloadLink);
    }

    @Step("Getting Sues number")
    private String getSueNumberFromFile() {
        File downloadsDirectory = new File(DOWNLOADS_DIRECTORY);
        final int timeout = 15;

        // Wait for file to be downloaded, max timeout is 15 seconds
        Wait<File> wait = new FluentWait<>(downloadsDirectory)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(InterruptedException.class)
                .ignoring(Exception.class)
                .withMessage("File was not downloaded within %d seconds".formatted(timeout));

        // Timeout exception will be thrown if file is not found within 15 seconds
        wait.until(f -> {
            File[] dirContents = downloadsDirectory.listFiles();
            if (dirContents != null) {
                for (File file : dirContents) {
                    System.out.println("Checking file: " + file.getName());
                    if (!file.getName().contains(downloadedFileName)) {
                        System.out.println("File is still being downloaded");
                    }
                    if (file.getName().equals(downloadedFileName)) {
                        System.out.println("Catalog.xml found");
                        return true;
                    }
                }
            }
            // File was not downloaded in 15 seconds
            return false;
        });

        File catalogFile = new File(DOWNLOADS_DIRECTORY, downloadedFileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(new FileInputStream(catalogFile).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Parse the XML string
        Document doc = null;
        try {
            doc = builder.parse(inputStream);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        // Normalize the XML structure
        doc.getDocumentElement().normalize();

        // Find the <number> elements
        NodeList nodeList = doc.getElementsByTagName("number");

        // Loop through all the nodes in the NodeList
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // Check for Sue's number
                if ("Sue".equals(element.getAttribute("id"))) {
                    String prefix = element.getElementsByTagName("prefix").item(0).getTextContent();
                    String number = element.getElementsByTagName("number").item(0).getTextContent();
                    System.out.println("Sue's prefix: " + prefix);
                    System.out.println("Sue's number: " + number);
                    System.out.println("----------------------------");
                    return prefix + number; // Combine and return the number
                }
            }
        }
        return null;
    }

    @Step("Entering Sue's number")
    public void enterSuesNumber() {
        String suesNumber = getSueNumberFromFile();
        if (suesNumber != null) {
            pageInteractionHelper.enterText(suesNumberInputField, suesNumber);
            System.out.println("Sue's number entered successfully");
        } else {
            System.out.println("Sue's number was not entered");
        }
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "72946";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}