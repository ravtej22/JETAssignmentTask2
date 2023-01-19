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

public class toiletSeatDownTest extends BaseTest {

  @Test(description = "Validate toiletseat-down API response in case valid Request")
  public void toiletSeatDownPositiveTest() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.post("/toiletseat-down");
    ResponseBody body = response.getBody();
    SuccessResponse successResponse = body.as(SuccessResponse.class);
    ExtentReport.info(successResponse.toString());
    Log.info("Response :" + response.prettyPrint());
    Assert.assertEquals(successResponse.getAction(), "toiletseat-down");
    Assert.assertNull(successResponse.getData());
    Assert.assertTrue(successResponse.isSuccess());

  }

  @Test(description = "Validate toiletseat-down API response in case of invalid Token")
  public void toiletSeatDownInvalidToken() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken + "as");
    Response response = request.post("/toiletseat-down");
    ResponseBody body = response.getBody();
    ErrorResponses errorResponses = body.as(ErrorResponses.class);
    ExtentReport.info(errorResponses.toString());
    Log.info("Response :" + response.prettyPrint());
    Assert.assertEquals(errorResponses.getError(), "invalid_token");
    Assert.assertEquals(errorResponses.getErrorDescription(), "The access token provided is invalid");
  }
  @Test(description = "Validate toiletseat-down API response in case of Invalid Method Type")
  public void toiletSeatDownInvalidMethodType() {
    RequestSpecification request = RestAssured.given();
    request.auth().oauth2(accessToken);
    Response response = request.get("/toiletseat-down");
    Log.info("Response: "+response.prettyPrint());
    int responseStatusCode = response.getStatusCode();
    Assert.assertEquals(responseStatusCode, 405);
  }

}
