package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestUtil {
    private static final String BASE_URL = "https://spring-auth-production-fa40.up.railway.app";

    public static RequestSpecification buildRequest(Map<String,String> body, String token) {
        RequestSpecification request = io.restassured.RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body);

        if (body != null) {
            request.body(body);
        }

        if (token != null) {
            request.header("Authorization", "Bearer " + token);
        }

        return request;
    }

    public static Response sendPostRequest(String endpoint, Map<String,String> body) {
        return buildRequest(body, null)
                .post(endpoint);
    }

    public static Response sendPostRequestWithToken(String endpoint, Map<String,String> body, String token) {
        return buildRequest(body, token)
                .post(endpoint);
    }

    public static Response sendGetRequest(String endpoint) {
        return buildRequest(null, null)
                .get(endpoint);
    }

    public static Response sendGetRequestWithToken(String endpoint, String token) {
        return buildRequest(null, token)
                .get(endpoint);
    }

    public static Response sendGetRequestWithParamsAndToken(String endpoint, Map<String,String> params, String token) {
        return buildRequest(null, token)
                .params(params)
                .get(endpoint);
    }

    public static Response sendPutRequestWithParamsAndToken(String endpoint, Map<String,String> body, String token, Map<String,String> params) {
        return buildRequest(body, token)
                .params(params)
                .put(endpoint);
    }

    public static Response sendDeleteRequestWithParamsAndToken(String endpoint, String token, Map<String,String> params) {
        return buildRequest(null, token)
                .params(params)
                .delete(endpoint);
    }
}
