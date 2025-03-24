package utils;

import context.TestContext;
import io.restassured.response.Response;
import payload.UserRequest;
import payload.UserResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class ResponseValidator {


    public static void assertStatusCode( int expectedStatusCode) {
        Response response = TestContext.getInstance().getResponse();
        if (response == null) throw new AssertionError("Response is null");
        int actualStatusCode = response.getStatusCode();
        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
        }
    }

    public static void assertResponseObject(List<Map<String, String>> expectedFields) {
        Response response = TestContext.getInstance().getResponse();
        String username = response.getBody().jsonPath().get("data.username");
        String email = response.getBody().jsonPath().get("data.email");
        String token = response.getBody().jsonPath().get("data.token");
        List<String> roles = response.getBody().jsonPath().getList("data.roles");
//        if (token != null && token.startsWith("eyJ") && token.split("\\.").length == 3)

        UserRequest userRequest = TestContext.getInstance().getUserRequest();
        for (Map<String, String> field : expectedFields) {
            String fieldName = field.get("field");
            String expectedValue = field.get("value");

            if (fieldName.equals("token")) {
                assertNotNull(token);
            }
            if (fieldName.equals("roles")) {
                assertNotNull(roles);
                List<String> expectedRoles = Arrays.asList(expectedValue.split("\\s+"));
                assertTrue(roles.containsAll(expectedRoles));
            }
            if (fieldName.equals("username")) {
                if (expectedValue.equals("<generated_username>")) {
                    assertEquals(userRequest.getUsername(), username);
                } else {
                    assertEquals(username, expectedValue);
                }
            }
            if (fieldName.equals("email")) {
                if (expectedValue.equals("<generated_email>")) {
                    assertEquals(userRequest.getEmail(), email);
                } else {
                    assertEquals(email, expectedValue);

                }
            }
        }

    }

    public static void assertErrorFieldAndMessage(String expectedField, String expectedMessage) {
        String actualMessage = TestContext.getInstance().getResponse().jsonPath().getString("data." + expectedField);
        if (actualMessage == null || !actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Expected message for field " + expectedField + ": " + expectedMessage + " but got: " + actualMessage);
        }
    }

    public static void verifyPaginationMetadata() {
        Response response = TestContext.getInstance().getResponse();

        assertNotNull(response);

        Integer pageNumber = response.getBody().jsonPath().get("data.pageNumber");
        Integer pageSize = response.getBody().jsonPath().get("data.pageSize");
        Integer totalPages = response.getBody().jsonPath().get("data.totalPages");
        Integer totalElements = response.getBody().jsonPath().get("data.totalElements");
        Boolean lastPage = response.getBody().jsonPath().get("data.lastPage");

        assertNotNull(pageNumber);
        assertTrue(pageNumber >= 0);
        assertNotNull(pageSize);
        assertTrue(pageSize > 0);
        assertNotNull(totalPages);
        assertTrue(totalPages > 0);
        assertNotNull(totalElements);
        assertTrue(totalElements > 0);
        assertNotNull(lastPage);
    }

    public static void verifyUserContent() {
        Response response = TestContext.getInstance().getResponse();
        List<Map<String, Object>> actualContent = response.getBody().jsonPath().getList("data.content");

        assertNotNull(actualContent);
        assertFalse(actualContent.isEmpty());

        for (Map<String, Object> content : actualContent) {
            String username = content.get("username").toString();
            String email = content.get("email").toString();
            Object rolesObj = content.get("roles");

            assertNotNull(username);
            assertFalse(username.isEmpty());
            assertNotNull(email);
            assertFalse(email.isEmpty());
            assertTrue(email.contains("@"));
            if (!(rolesObj instanceof List<?>)) {
                throw new AssertionError("Roles are null or not a valid list");
            } else {
                List<String> roles = ((List<?>) rolesObj).stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .toList();
                assertNotNull(roles);
                assertFalse(roles.isEmpty());
                for (String role : roles) {
                    assertNotNull(role);
                    assertTrue(role.startsWith("ROLE_"));
                }
            }
        }
    }

}
