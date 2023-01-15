package org.symfonycasts.tests;

import io.restassured.http.ContentType;
import org.symfonycasts.utils.PropertyUtils;
import org.symfonycasts.utils.RestAssuredUtil;
import org.symfonycasts.utils.TestUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {


  //Instantiate a Helper Test Methods (testUtils) Object
  TestUtil testUtil = new TestUtil();
  public String accessToken;


  @BeforeClass
  public void setup() {
    //Test Setup
    RestAssuredUtil.setBaseURI(); //Setup Base URI
    accessToken = testUtil.getAccessToken();

//    RestAssuredUtil.setAccessToken(testUtil.getAccessToken());
    RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type
    RestAssuredUtil.setBasePath("api/" + PropertyUtils.userID); //Setup Base Path


  }

  @AfterClass
  public void afterTest() {
    //Reset Values
    RestAssuredUtil.resetBaseURI();
    RestAssuredUtil.resetBasePath();
  }
}
