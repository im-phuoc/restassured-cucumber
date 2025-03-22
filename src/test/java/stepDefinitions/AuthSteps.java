package stepDefinitions;

import payload.request.LoginRequest;
import payload.request.RegisterRequest;
import services.AuthService;
import utils.FakerUtil;
import utils.TestContext;
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
        TestContext.getInstance().setLoginRequest(new LoginRequest(username, password));
        TestContext.getInstance().setResponse(authService.login(endpoint, username, password));
    }

    @Given("I send a register request to {string} with random credentials")
    public void registerRandom(String endpoint) {
        RegisterRequest registerRequest = FakerUtil.generateRegisterRequest();
        TestContext.getInstance().setRegisterRequest(registerRequest);
        TestContext.getInstance().setResponse(authService.register(endpoint, registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword()));
    }

    @Given("I send a register request to {string} with the following credentials:")
    public void register(String endpoint, List<Map<String, String>> credentialsTable) {
        Map<String, String> credentials = credentialsTable.get(0);
        String username = credentials.get("username");
        String email = credentials.get("email");
        String password = credentials.get("password");
        RegisterRequest registerRequest = new RegisterRequest(username, email, password);
        TestContext.getInstance().setRegisterRequest(registerRequest);
        TestContext.getInstance().setResponse(authService.register(endpoint,username, email, password));
    }
}
