package stepDefinitions;

import io.cucumber.java.en.And;
import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utils.ResponseValidator;

import java.util.List;
import java.util.Map;

public class CommonSteps {
    private Response response;

    @Then("The status code is {int}")
    public void verifyStatusCode(int statusCode) {
        ResponseValidator.assertStatusCode( statusCode);
    }

    @Then("The response contains an object with the following fields:")
    public void verifyResponseObject(List<Map<String, String>> fieldTable) {
        ResponseValidator.assertResponseObject(fieldTable);
    }


    @And("The response contains an error with field {string} and message {string}")
    public void verifyFieldAndMessage(String field, String message) {
        ResponseValidator.assertErrorFieldAndMessage(field, message);
    }

    @And("The response contains a pagination list with valid metadata")
    public void verifyPaginationMetadata() {
        ResponseValidator.verifyPaginationMetadata();
    }

    @And("The response contains a list of valid users")
    public void verifyUserList() {
        ResponseValidator.verifyUserContent();
    }

}
