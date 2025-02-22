package com.harshitbhardwaj.pages;

import com.harshitbhardwaj.support.PageInteractionHelper;
import com.harshitbhardwaj.support.TestHelper;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.testng.SkipException;

import java.util.List;

import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;
import static io.restassured.RestAssured.given;

public class TestdataInAServicePage implements TestHelper {

    private final PageInteractionHelper pageInteractionHelper;

    private final By createTDSButton = By.cssSelector("a#createTDS");
    private final By attributeLocator = By.cssSelector("td#attribute");
    private final By key = By.cssSelector("td#key");
    private final By submitButton = By.cssSelector("a#submit");
    private final By inputField = By.cssSelector("input#result");

    public TestdataInAServicePage(PageInteractionHelper pageInteractionHelper) {
        this.pageInteractionHelper = pageInteractionHelper;
    }

    @Step("Clicking on Create TDS Button")
    public void clickOnCreateTDSButton() {
        pageInteractionHelper.clickOnElement(createTDSButton);
    }

    @Step("Extracting the key text against which attribute has to be searched in API response")
    private String getKeyTextToBeExtracted() {
        return pageInteractionHelper.getText(key);
    }

    @Step("Extracting the attribute to be searched in API response")
    private String getAttributeTextToBeExtracted() {
        return pageInteractionHelper.getText(attributeLocator);
    }

    @Step("Hitting the endpoint to get the data and fetching the attribute value against the fetched key")
    private String parseResponse() {
        String endpoint = "https://tdsservice.azurewebsites.net/data/obstacle";
        Response response = given().when().get(endpoint).then().extract().response();

        if (response.getStatusCode() != 200) {
            System.out.println("Response status code: " + response.getStatusCode());
            throw new SkipException("Request failed with status code: " + response.getStatusCode());
        }

        // Beautify the JSON response and print it
        System.out.println("Beautified JSON Response:");
        System.out.println(response.prettyPrint());

        String key = getKeyTextToBeExtracted();
        System.out.println("Key to be extracted: " + key);

        String attributeKey = getAttributeTextToBeExtracted();
        System.out.println("Attribute of " + key + " to be extracted: " + attributeKey);

        // Get the raw response body as a string
        String responseBody = response.getBody().asString();

        // Use JsonPath to parse the raw JSON response string
        JsonPath jsonPath = new JsonPath(responseBody);

        // Find the specific record where "data.key" matches the key
        List<Object> matchingRecords = jsonPath.getList("findAll { it.data.key == '" + key + "' }");

        if (!matchingRecords.isEmpty()) {
            System.out.println("Matching Record:");
            System.out.println(matchingRecords.getFirst());

            // Extracting the dynamic attribute (e.g., "name") based on the key
            String result = jsonPath.getString("find { it.data.key == '" + key + "' }.data." + attributeKey);
            System.out.println(attributeKey + " of the record with key " + key + ": " + result);
            return result;
        } else {
            System.out.println("No matching record found.");
            throw new SkipException("No matching record found for key: " + key);
        }
    }

    @Step
    public void enter() {
        String result = parseResponse();
        if (result != null) {
            pageInteractionHelper.enterText(inputField, result);
        }
    }

    @Step("Clicking on Submit Button")
    public void clickOnSubmit() {
        pageInteractionHelper.clickOnElement(submitButton);
    }

    @Override
    public void navigateToObstacle() {
        String obstacleId = "16384";
        pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
    }

    @Override
    public boolean isTestPassed() {
        return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
    }
}