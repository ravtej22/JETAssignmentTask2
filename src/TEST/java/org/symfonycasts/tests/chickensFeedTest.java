package org.symfonycasts.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.symfonycasts.pojo.responses.ErrorResponses;
import org.symfonycasts.pojo.responses.SuccessResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class chickensFeedTest extends BaseTest{

  @Test
  public void chickensFeedPositiveTest() {

    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.post("/chickens-feed");
    ResponseBody body = response.getBody();
    SuccessResponse successResponse = body.as(SuccessResponse.class);
    Assert.assertEquals(successResponse.getAction(), "chickens-feed");
    Assert.assertNull(successResponse.getData());
//    Assert.assertEquals(successResponse.getMessage(), "Your chickens are now full and happy");


  }

  @Test
  public void chickensFeedInvalidToken() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken + "as");
    Response response = request.post("/chickens-feed");
    ResponseBody body = response.getBody();
    ErrorResponses errorResponses = body.as(ErrorResponses.class);
    Assert.assertEquals(errorResponses.getError(), "invalid_token");
    Assert.assertEquals(errorResponses.getErrorDescription(), "The access token provided is invalid");
  }
}
