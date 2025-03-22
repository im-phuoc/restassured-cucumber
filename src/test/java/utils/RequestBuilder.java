package utils;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestBuilder {
    private RequestSpecification request;

    public RequestBuilder() {
        reset();
    }

    private void reset() {
        request = RestAssured.given()
                .baseUri(TestConfig.BASE_URL)
                .contentType("application/json");
    }

    public RequestBuilder withToken(String token) {
        if (token != null && !token.isEmpty()) {
            request.header("Authorization", "Bearer " + token);
        }
        return this;
    }

    public RequestBuilder withBody(Map<String,?> body) {
        if (body != null && !body.isEmpty()) {
            request.body(body);
        }
        return this;
    }

    public RequestBuilder withPathParams(String key, String value) {
        if (key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
            request.pathParam(key, value);
        }
        return this;
    }

    public Response get(String endpoint) {
        Response response = request.get(endpoint);
        reset();
        return response;
    }

    public Response post(String endpoint) {
        Response response = request.post(endpoint);
        reset();
        return response;
    }

    public Response put(String endpoint) {
        Response response = request.put(endpoint);
        reset();
        return response;
    }

    public Response delete(String endpoint) {
        Response response = request.delete(endpoint);
        reset();
        return response;
    }
}
