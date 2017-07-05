package ro.dcsi.internship;

public class IntegrationTestConfig {
  public static OpenIdConfig testInstance = new OpenIdConfig("http://localhost:8080", "openidm-admin",
      "openidm-admin");
}
