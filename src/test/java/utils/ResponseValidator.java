package utils;

import io.restassured.response.Response;
import payload.response.LoginResponse;
import payload.response.RegisterResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ResponseValidator {

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        if (response == null) throw new AssertionError("Response is null");
        int actualStatusCode = response.getStatusCode();
        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
        }
    }

    public static void assertResponseObject(Response response, List<Map<String, String>> expectedFields, TestContext testContext) {
        String username = response.getBody().jsonPath().get("data.username");
        String email = response.getBody().jsonPath().get("data.email");
        String token = response.getBody().jsonPath().get("data.token");
        List<String> roles = response.getBody().jsonPath().getList("data.roles");

        if (token != null && token.startsWith("eyJ") && token.split("\\.").length == 3) {
            testContext.setLoginResponse(new LoginResponse(token,username,email,roles));
        } else {
            testContext.setRegisterResponse(new RegisterResponse(username,email,roles));
        }
        for (Map<String, String> field : expectedFields) {
            String fieldName = field.get("field");
            String expectedValue = field.get("value");

            if ("<generated_username>".equals(expectedValue)) {
                if (token == null && testContext.getRegisterRequest() != null) {
                    expectedValue = testContext.getRegisterRequest().getUsername();
                } else {
                    throw new AssertionError("Placeholder <generated_username> is only valid for random register scenario");
                }
            } else if ("<generated_email>".equals(expectedValue)) {
                if (token == null && testContext.getRegisterRequest() != null) {
                    expectedValue = testContext.getRegisterRequest().getEmail();
                } else {
                    throw new AssertionError("Placeholder <generated_email> is only valid for random register scenario");
                }
            } else if ("<any>".equals(expectedValue) && "token".equals(fieldName)) {
                if (token == null || token.isEmpty()) {
                    throw new AssertionError("Expected a non-empty token but got: " + token);
                }
                continue;
            }

            if ("roles".equals(fieldName)) {
                List<String> actualRoles = response.jsonPath().getList("data.roles");
                List<String> expectedRoles = Arrays.asList(expectedValue.replace("[", "").replace("]", "").split(", "));
                if (!actualRoles.equals(expectedRoles)) {
                    throw new AssertionError("Expected roles: " + expectedRoles + " but got: " + actualRoles);
                }
            } else {
                String actualValue = response.jsonPath().getString("data." + fieldName);
                if (actualValue == null || !actualValue.equals(expectedValue)) {
                    throw new AssertionError("Expected value for field " + fieldName + ": " + expectedValue + " but got: " + actualValue);
                }
            }
        }
    }

    public static void assertErrorFieldAndMessage(Response response, String expectedField, String expectedMessage) {
        String actualMessage = response.jsonPath().getString("data." + expectedField);
        if (actualMessage == null || !actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Expected message for field " + expectedField + ": " + expectedMessage + " but got: " + actualMessage);
        }
    }

}
