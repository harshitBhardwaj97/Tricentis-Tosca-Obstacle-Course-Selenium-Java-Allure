package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

public class BeFastAutomatePage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By loadBooksButton = By.xpath("//a[.='Load Books']");
    private final By xmlTextArea = By.xpath("//textarea[@id='books']");
    private final By isbnInputField = By.cssSelector("input#isbn");

    public BeFastAutomatePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Load Books button")
    public void clickOnLoadBooksButton() {
        pageInteractionHelper.clickOnElement(loadBooksButton);
    }

    @Step("Getting the XML content as string")
    private String getXmlContentAsString() {
        System.out.println(pageInteractionHelper.getElementValueProperty(xmlTextArea));
        return pageInteractionHelper.getElementValueProperty(xmlTextArea);
    }

    @Step("Getting the ISBN of the book with title 'Testing Computer Software'")
    private String getISBN() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        // Convert the string to InputStream
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(getXmlContentAsString().getBytes(StandardCharsets.UTF_8));

        // Parse the XML string
        Document doc = null;
        try {
            doc = builder.parse(inputStream);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        // Normalize the XML structure
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("book");

        // Loop through all the nodes in the NodeList
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String isbn = element.getElementsByTagName("isbn").item(0).getTextContent();
                String title = element.getElementsByTagName("title").item(0).getTextContent();
                System.out.println("ISBN: " + isbn + " and title: " + title);
                if (title.equals("Testing Computer Software")) {
                    return isbn;
                }
            }
        }
        throw new AssertionError("This shouldn't happen");
    }

    @Step("Entering the ISBN")
    public void enterISBN() {
        pageInteractionHelper.enterText(isbnInputField, getISBN());
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "87912";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}