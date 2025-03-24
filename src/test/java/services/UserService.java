package services;

import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import utils.RequestBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {
    String token = TestContext.getInstance().getResponse().getBody().jsonPath().get("data.token");

    public Response getAllUsers(String endpoint, String page, String size) {
        return new RequestBuilder().withToken(token).withParams(page,size).get(endpoint);
    }

    public Response getUserByUsername(String endpointTemplate, String pathVariable, String value) {
        String endpoint = endpointTemplate.replace("{" + pathVariable + "}", value);
        return new RequestBuilder().withToken(token).get(endpoint);
    }

    public Response updateRolesByUsername(String endpointTemplate, String pathVariable, String value, DataTable dataTable) {
        String endpoint = endpointTemplate.replace("{" + pathVariable + "}", value);
        List<String> roles = dataTable.asList().stream().skip(1).filter(role->role!=null && !role.isBlank()).collect(Collectors.toList());
        Map<String, Object> body = new HashMap<>();
        body.put("roles", roles);
        return new RequestBuilder().withToken(token).withBody(body).put(endpoint);
    }

    public Response deleteUserByUsername(String endpointTemplate, String pathVariable, String value) {
        String endpoint = endpointTemplate.replace("{" + pathVariable + "}", value);
        return new RequestBuilder().withToken(token).delete(endpoint);
    }


}
