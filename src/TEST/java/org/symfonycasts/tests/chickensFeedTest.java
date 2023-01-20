package org.symfonycasts.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.symfonycasts.pojo.responses.ErrorResponses;
import org.symfonycasts.pojo.responses.SuccessResponse;
import org.symfonycasts.reports.ExtentReport;
import org.symfonycasts.utils.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class chickensFeedTest extends BaseTest {

  @Test(description = "Validate chickens-feed API Response in case of valid Request")
  public void chickensFeedPositiveTest() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.post("/chickens-feed");
    ResponseBody body = response.getBody();
    SuccessResponse successResponse = body.as(SuccessResponse.class);
    ExtentReport.info(successResponse.toString());
    Log.info("Response :"+response.prettyPrint());
    Assert.assertEquals(successResponse.getAction(), "chickens-feed");
    Assert.assertNull(successResponse.getData());
    Assert.assertTrue(successResponse.isSuccess());


  }

  @Test(description = "Validate chickens-feed API response in case of Invalid Token")
  public void chickensFeedInvalidToken() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken + "as");
    Response response = request.post("/chickens-feed");
    ResponseBody body = response.getBody();
    ErrorResponses errorResponses = body.as(ErrorResponses.class);
    ExtentReport.info(errorResponses.toString());
    Log.info("Response :"+response.prettyPrint());
    Assert.assertEquals(errorResponses.getError(), "invalid_token");
    Assert.assertEquals(errorResponses.getErrorDescription(), "The access token provided is invalid");
  }

  @Test(description = "Validate chickens-feed API response in case of Invalid Method Type")
  public void chickensFeedInvalidMethodType() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.get("/chickens-feed");
    int responseStatusCode = response.getStatusCode();
    Assert.assertEquals(responseStatusCode, 405);
  }
}
