package stepDefinitions;

import io.cucumber.java.en.And;
import utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ResponseValidator;

import java.util.List;
import java.util.Map;

public class CommonSteps {
    private Response response;

    @Then("The status code is {int}")
    public void verifyStatusCode(int statusCode) {
        ResponseValidator.assertStatusCode(TestContext.getInstance().getResponse(), statusCode);
    }

    @Then("The response contains an object with the following fields:")
    public void verifyResponseObject(List<Map<String, String>> fieldTable) {
        ResponseValidator.assertResponseObject(TestContext.getInstance().getResponse(), fieldTable, TestContext.getInstance());
    }


    @And("The response contains an error with field {string} and message {string}")
    public void verifyFieldAndMessage(String field, String message) {
        ResponseValidator.assertErrorFieldAndMessage(TestContext.getInstance().getResponse(), field, message);
    }

}
