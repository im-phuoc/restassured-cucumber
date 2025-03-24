package services;

import context.TestContext;
import io.restassured.response.Response;
import utils.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

public class AuthService {


    public Response login(String endpoint, String username, String password) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("username", username);
        body.put("password", password);
        return new RequestBuilder().withBody(body).post(endpoint);
    }

    public Response register(String endpoint,String username, String email, String password) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("username", username);
        body.put("email", email);
        body.put("password", password);
        return new RequestBuilder().withBody(body).post(endpoint);
    }
}
