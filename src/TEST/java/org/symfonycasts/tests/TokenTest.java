package org.symfonycasts.tests;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.symfonycasts.pojo.requests.TokenRequest;
import org.symfonycasts.pojo.responses.TokenResponse;
import org.symfonycasts.utils.PropertyUtils;
import org.testng.annotations.Test;

public class TokenTest {

  @Test public void getAccessToken() {
    RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
    RequestSpecification request = RestAssured.given();
    TokenRequest tokenRequest = TokenRequest.builder().client_id(PropertyUtils.clientId).client_secret(PropertyUtils.clientSecret).grant_type(PropertyUtils.grantType).build();
    ObjectMapper mapObject = new ObjectMapper();
    Map<String, Object> mapObj = mapObject.convertValue(tokenRequest, Map.class);
    request.formParams(mapObj);
    request.log().all();
    Response response = request.post("/token");
    ResponseBody body = response.getBody();
    TokenResponse responseBody = body.as(TokenResponse.class);
  }

}

