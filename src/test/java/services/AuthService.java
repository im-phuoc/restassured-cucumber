package services;

import io.restassured.response.Response;
import payload.response.LoginResponse;
import payload.response.RegisterResponse;
import utils.RequestBuilder;
import utils.TestContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthService {


    public Response login(String endpoint, String username, String password) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("username", username);
        body.put("password", password);
        Response response = new RequestBuilder().withBody(body).post(endpoint);
        if (response.getStatusCode() == 200) {
            String responseToken = response.jsonPath().get("data.token");
            String responseUsername = response.jsonPath().get("data.username");
            String responseEmail = response.jsonPath().get("data.email");
            List<String> responseRoles = response.jsonPath().getList("data.roles", String.class);

            LoginResponse loginResponse = new LoginResponse(responseToken, responseUsername, responseEmail, responseRoles);
            TestContext.getInstance().setLoginResponse(loginResponse);
        }
        return response;
    }

    public Response register(String endpoint,String username, String email, String password) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("username", username);
        body.put("email", email);
        body.put("password", password);

        Response response = new RequestBuilder().withBody(body).post(endpoint);

        if (response.getStatusCode() == 200) {
            String responseUsername = response.jsonPath().get("data.username");
            String responseEmail = response.jsonPath().get("data.email");
            List<String> responseRoles = response.jsonPath().getList("data.roles", String.class);

            RegisterResponse registerResponse = new RegisterResponse(responseUsername, responseEmail, responseRoles);
            TestContext.getInstance().setRegisterResponse(registerResponse);
        }
        return response;
    }
}
