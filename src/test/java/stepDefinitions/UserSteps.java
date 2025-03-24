package stepDefinitions;


import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import services.UserService;


public class UserSteps {
    private final UserService userService = new UserService();

    @When("I send a GET request to {string}")
    public void getAllUsers(String endpoint) {
        TestContext.getInstance().setResponse(userService.getAllUsers(endpoint,null,null));
    }

    @When("I send a GET request to {string} with path variable {string} as {string}")
    public void getUserByUsername(String endpoint, String pathVariable, String value) {
        TestContext.getInstance().setResponse(userService.getUserByUsername(endpoint,pathVariable,value));
    }

    @When("I send a PUT request to {string} with path variable {string} as {string} and the following roles:")
    public void updateRoleByUsername(String endpoint, String pathVariable, String value, DataTable dataTable) {
        TestContext.getInstance().setResponse(userService.updateRolesByUsername(endpoint,pathVariable,value,dataTable));
    }

    @When("I send a DELETE request to {string} with path variable {string} as {string}")
    public void deleteUserByUsername(String endpoint, String pathVariable, String value) {
        TestContext.getInstance().setResponse(userService.deleteUserByUsername(endpoint,pathVariable,value));
    }
}
