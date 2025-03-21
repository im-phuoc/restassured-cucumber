package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.RequestUtil;
import utils.ResponseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthSteps {
    private Response response;

    @Given("I send a POST request to {string} with body:")
    public void sendPostRequest(String endpoint, List<Map<String, String>> dataTable) {
        Map<String,String> body = new HashMap<>(dataTable.get(0));
        response = RequestUtil.sendPostRequest(endpoint, body);
    }


    @When("I receive a response")
    public void receiveResponse() {
    }


    @Then("The status code is {int}")
    public void verifyStatusCode(int statusCode) {
        ResponseUtil.verifyStatusCode(response,statusCode);
    }


    @And("The response contains an error for {string} with message {string}")
    public void verifyErrorMessage(String fieldName, String message) {
        ResponseUtil.verifyErrorMessage(response, fieldName, message);
    }


    @And("The response contains a valid token")
    public void verifyToken() {
        ResponseUtil.verifyToken(response);
    }


    @And("The response contains the field {string} with value {string}")
    public void verifyFieldValue(String fieldName, String message) {
        ResponseUtil.verifyFieldValue(response, fieldName, message);
    }

    @And("The response contains all roles:")
    public void verifyAllRoles(List<Map<String,String>> roleTable) {
        List<String> roles = roleTable.stream().map(row -> row.get("role")).collect(Collectors.toList());
        ResponseUtil.verifyAllRoles(response, roles);
    }

    @And("The response contains an error with message {string}")
    public void verifySimpleMessage(String message) {
        ResponseUtil.verifySimpleMessage(response, message);
    }
}
