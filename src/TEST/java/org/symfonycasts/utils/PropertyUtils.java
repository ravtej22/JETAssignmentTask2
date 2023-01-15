package org.symfonycasts.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
  static Properties properties;
  public static String baseURI;
  public static String userID;

  public static String clientId;
  public static String clientSecret;
  public static String grantType;

  static {
    baseURI = getProperty("baseUrl");
    clientSecret = getProperty("clientSecret");
    clientId = getProperty("clientId");
    grantType = getProperty("grantType");
    userID=getProperty("userID");

    if (baseURI == null) {
      baseURI = "";

    }
  }

  private static void loadProperty() throws IOException {
    if (properties == null) {
      properties = new Properties();
      properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/TEST/resources/configs/configs.properties"));
    }
  }

  public static String getProperty(String property) {
    try {
      loadProperty();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return properties.getProperty(property);
  }
}