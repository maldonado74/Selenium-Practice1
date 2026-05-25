package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtility {

    public Response sendGetRequest(String endpointUrl) {

        Response response =
                given()
                .when()
                .get(endpointUrl);

        return response;
    }
    public String getJsonValue(Response response, String jsonPath) {

        return response.jsonPath().getString(jsonPath);
    }
}