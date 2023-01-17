package org.symfonycasts.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.symfonycasts.pojo.responses.ErrorResponses;
import org.symfonycasts.pojo.responses.SuccessResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnlockTheBarnTest extends BaseTest {

  @Test
  public void unlockTheBarnPositiveTest() {

    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.post("/barn-unlock");
    ResponseBody body = response.getBody();
    SuccessResponse successResponse = body.as(SuccessResponse.class);
    Assert.assertEquals(successResponse.getAction(), "barn-unlock");
    Assert.assertNull(successResponse.getData());
    Assert.assertTrue(successResponse.isSuccess());

  }

  @Test
  public void unlockTheBarnInvalidToken() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken + "as");
    Response response = request.post("/barn-unlock");
    ResponseBody body = response.getBody();
    ErrorResponses errorResponses = body.as(ErrorResponses.class);
    Assert.assertEquals(errorResponses.getError(), "invalid_token", "");
    Assert.assertEquals(errorResponses.getErrorDescription(), "The access token provided is invalid");
  }

  @Test(description = "")
  public void unlockTheBarnInvalidMethodType() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.get("/barn-unlock");
    int responseStatusCode = response.getStatusCode();
    Assert.assertEquals(responseStatusCode, 405);
  }

}
