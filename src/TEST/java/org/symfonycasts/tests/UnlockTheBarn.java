package org.symfonycasts.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.symfonycasts.pojo.responses.ErrorResponses;
import org.symfonycasts.pojo.responses.unlockBarnResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnlockTheBarn extends BaseTest {

  @Test
  public void unlockTheBarnPositiveTest() {

    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.post("/barn-unlock");
    ResponseBody body = response.getBody();
    unlockBarnResponse unlockTheBarn = body.as(unlockBarnResponse.class);
    Assert.assertEquals(unlockTheBarn.getAction(), "barn-unlock");
    Assert.assertNull(unlockTheBarn.getData());

  }

  @Test
  public void unlockTheBarnInvalidToken() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken + "as");
    Response response = request.post("/barn-unlock");
    ResponseBody body = response.getBody();
    ErrorResponses errorResponses = body.as(ErrorResponses.class);
    Assert.assertEquals(errorResponses.getError(), "invalid_token");
    Assert.assertEquals(errorResponses.getErrorDescription(), "The access token provided is invalid");
  }
}
