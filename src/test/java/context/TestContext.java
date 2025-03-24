package context;

import io.restassured.response.Response;
import payload.UserRequest;
import payload.UserResponse;


public class TestContext {
    private static final ThreadLocal<TestContext> instance = ThreadLocal.withInitial(TestContext::new);
    private Response response;
    private UserRequest userRequest;
    private UserResponse userResponse;

    public static TestContext getInstance() {
        return instance.get();
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public void remove() {
        instance.remove();
    }
}
