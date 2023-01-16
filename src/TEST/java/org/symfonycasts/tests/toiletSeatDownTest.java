package org.symfonycasts.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.symfonycasts.pojo.responses.ErrorResponses;
import org.symfonycasts.pojo.responses.SuccessResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class toiletSeatDownTest extends BaseTest {

  @Test
  public void toiletSeatDownPositiveTest(){

    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.post("/toiletseat-down");
    ResponseBody body = response.getBody();
    body.prettyPeek();
    SuccessResponse successResponse = body.as(SuccessResponse.class);
    Assert.assertEquals(successResponse.getAction(), "toiletseat-down");
    Assert.assertNull(successResponse.getData());
    Assert.assertEquals(successResponse.getMessage(), "You just put the toilet seat down. You're a wonderful roommate!");
  }
  @Test
  public void toiletSeatDownInvalidToken() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken + "as");
    Response response = request.post("/toiletseat-down");
    ResponseBody body = response.getBody();
    ErrorResponses errorResponses = body.as(ErrorResponses.class);
    Assert.assertEquals(errorResponses.getError(), "invalid_token");
    Assert.assertEquals(errorResponses.getErrorDescription(), "The access token provided is invalid");
  }

}
