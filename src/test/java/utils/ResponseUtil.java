package utils;

import io.restassured.response.Response;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;

public class ResponseUtil {

    public static void verifyStatusCode(Response response, int statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    public static void verifyErrorMessage(Response response, String fieldName ,String errorMessage) {
        response.then()
                .body("data." + fieldName, equalTo(errorMessage));
    }

    public static void verifySimpleMessage(Response response, String message) {
        response.then()
                .body("data", equalTo(message));
    }

    public static void verifyToken(Response response) {
        response.then()
                .body("data.token", notNullValue())
                .body("data.token",startsWith("eyJ"));
    }

    public static void verifyFieldValue(Response response, String fieldName, String fieldValue) {
        response.then()
                .body("data." + fieldName, equalTo(fieldValue));
    }

    public static void verifyAllRoles(Response response, List<String> roles) {
        response.then()
                .body("data.roles", containsInAnyOrder(roles.toArray()));
    }

    public static void verifyListNotEmpty(Response response) {
        response.then()
                .body("data", not(empty()));
    }
}
