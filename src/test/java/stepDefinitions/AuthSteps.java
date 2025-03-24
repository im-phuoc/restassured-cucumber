package stepDefinitions;

import io.restassured.response.Response;
import payload.UserRequest;
import payload.UserResponse;
import services.AuthService;
import utils.FakerUtil;
import context.TestContext;
import io.cucumber.java.en.Given;


import java.util.List;
import java.util.Map;

public class AuthSteps {
    private final AuthService authService = new AuthService();

    @Given("I send a login request to {string} with the following credentials:")
    public void login(String endpoint, List<Map<String, String>> credentialsTable) {
        Map<String, String> credentials = credentialsTable.get(0);
        String username = credentials.get("username");
        String password = credentials.get("password");
        TestContext.getInstance().setResponse(authService.login(endpoint, username, password));
    }

    @Given("I send a register request to {string} with random credentials")
    public void registerRandom(String endpoint) {
        UserRequest userRequestRegister = FakerUtil.generateRegisterRequest();
//        UserRequest userResponse = new UserResponse(userRequestRegister.getUsername(), userRequestRegister.getEmail());
        TestContext.getInstance().setUserRequest(userRequestRegister);
        TestContext.getInstance().setResponse(authService.register(endpoint, userRequestRegister.getUsername(), userRequestRegister.getEmail(), userRequestRegister.getPassword()));
    }

    @Given("I send a register request to {string} with the following credentials:")
    public void register(String endpoint, List<Map<String, String>> credentialsTable) {
        Map<String, String> credentials = credentialsTable.get(0);
        String username = credentials.get("username");
        String email = credentials.get("email");
        String password = credentials.get("password");
        TestContext.getInstance().setResponse(authService.register(endpoint,username, email, password));
    }
}
