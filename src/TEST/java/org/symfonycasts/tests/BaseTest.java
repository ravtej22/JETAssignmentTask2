package org.symfonycasts.tests;

import io.restassured.http.ContentType;
import org.symfonycasts.utils.PropertyUtils;
import org.symfonycasts.utils.RestAssuredUtil;
import org.symfonycasts.utils.TestUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {


  //Instantiate a Helper Test Methods (testUtils) Object
  TestUtil testUtil = new TestUtil();
  public static String accessToken;


  @BeforeSuite
  public void setup() {
    //Test Setup
    RestAssuredUtil.setBaseURI();
    //Setup Base URI
    accessToken =testUtil.getAccessToken();
    RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type
    RestAssuredUtil.setBasePath("api/" + PropertyUtils.userID); //Setup Base Path
  }



  @AfterSuite
  public void afterTest() {
    //Reset Values
    RestAssuredUtil.resetBaseURI();
    RestAssuredUtil.resetBasePath();
  }
}
