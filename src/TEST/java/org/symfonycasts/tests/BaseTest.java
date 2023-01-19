package org.symfonycasts.tests;

import io.restassured.http.ContentType;
import org.symfonycasts.utils.PropertyUtils;
import org.symfonycasts.utils.RestAssuredUtil;
import org.symfonycasts.utils.TestUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {


  TestUtil testUtil = new TestUtil();
  public static String accessToken;

  /**
   * Setup Method to set BASE URL and access Token
   */
  @BeforeSuite
  public void setup() {
    RestAssuredUtil.setBaseURI();
    accessToken =testUtil.getAccessToken();
    RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type
    RestAssuredUtil.setBasePath("api/" + PropertyUtils.userID); //Setup Base Path
  }


  /**
   * After Test Setup to reset Base Path
   */
  @AfterSuite
  public void afterTest() {
    RestAssuredUtil.resetBaseURI();
    RestAssuredUtil.resetBasePath();
  }
}
