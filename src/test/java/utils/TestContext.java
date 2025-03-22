package utils;

import io.restassured.response.Response;
import payload.request.LoginRequest;
import payload.request.RegisterRequest;
import payload.response.LoginResponse;
import payload.response.RegisterResponse;

public class TestContext {
    private static final ThreadLocal<TestContext> threadLocal = ThreadLocal.withInitial(TestContext::new);
    private Response response;
    private LoginRequest loginRequest;
    private LoginResponse loginResponse;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;


    public static TestContext getInstance() {
        return threadLocal.get();
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void setRegisterResponse(RegisterResponse registerResponse) {
        this.registerResponse = registerResponse;
    }

    public RegisterResponse getRegisterResponse() {
        return registerResponse;
    }

    public void clear() {
        this.response = null;
        this.loginRequest = null;
        this.loginResponse = null;
        this.registerRequest = null;
        this.registerResponse = null;
    }

    public static void remove() {
        threadLocal.remove();
    }
}
