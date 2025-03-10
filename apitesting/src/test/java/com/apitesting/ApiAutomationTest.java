package com.apitesting;

import org.junit.jupiter.api.Assertions;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiAutomationTest {

	 // Define the base URL and endpoin
	
    public static String baseUrl = "https://reqres.in";
    public static String endpoint = "/api/users";
    public static String URL  = baseUrl + endpoint;

    public static void main(String[] args) {
        ApiAutomationTest test = new ApiAutomationTest();
        test.performGetRequestAndValidate(URL);
    }

    /**
     * Executes a GET request and validates the response.
     */
    public void performGetRequestAndValidate(String url) {
        try {
            Response response = sendGetRequest(url); 
            validateResponse(response);              
        } catch (Exception e) {
            System.out.println("Test Failed: An exception occurred!");
            e.printStackTrace(); 
        }
    }

    /**
     * Sends a GET request to the given URL.
     */
    private Response sendGetRequest(String url) {
        System.out.println("Sending GET request to URL: " + url);
        return RestAssured.get(url); 
    }

    /**
     * Validates the response from the API call.
     */
    private void validateResponse(Response response) {
        if (response != null) {
            int statusCode = response.getStatusCode(); // Capture HTTP status code
            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body:\n" + response.getBody().asPrettyString());

            // Hard Assertion: Fails the test if status code isn't 200
            Assertions.assertEquals(200, statusCode, "Expected status code is 200");

            // Success log after validation passes
            System.out.println("Test Passed: Status code is 200");
        } else {
            // Null check to avoid NullPointerException
            System.out.println("Test Failed: No response received");
        }
    }
}
