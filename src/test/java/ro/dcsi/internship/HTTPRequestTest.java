package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Test;

public class HTTPRequestTest {
  private static String existingUserId = "HTTPRequestTestExistingUser";
  private static String nonExistingUserId = "HTTPRequestTestNonExistingUser";
  private static OpenIdConfig c = IntegrationTestConfig.testInstance;

  @AfterClass
  public static void prepareDatabase() {
    ForgeRockUserDao db = new ForgeRockUserDao(IntegrationTestConfig.testInstance);
    db.deleteUser(existingUserId);
    db.deleteUser(nonExistingUserId);
    Hashtable<String, String> existingUserAttributes = new Hashtable<String, String>();
    existingUserAttributes.put("_id", existingUserId);
    existingUserAttributes.put("userName", existingUserId);
    existingUserAttributes.put("mail", "ExistingUser@ex.com");
    existingUserAttributes.put("sn", existingUserId);
    existingUserAttributes.put("givenName", existingUserId);
    User existingUser = new User(existingUserId, existingUserAttributes);
    db.addUser(existingUser);
  }

  @Test(timeout = 10000)
  public void prepareDatabaseTest() {
    HTTPRequestTest.prepareDatabase();
    ForgeRockUserDao db = new ForgeRockUserDao(IntegrationTestConfig.testInstance);
    Optional<User> user = db.getUser(existingUserId);
    assertTrue(user.isPresent());
    assertEquals(existingUserId, db.getUser(existingUserId).get().getId());
  }

  @Test(timeout = 10000)
  public void simpleTest() {
    HTTPRequestTest.prepareDatabase();
    Map<String, String> headers = new Hashtable<String, String>();
    headers.put("X-OpenIDM-Username", c.openIDMUsername);
    headers.put("X-OpenIDM-Password", c.openIDMPassword);
    headers.put("Content-Type", "application/json");
    String url = c.openIDMServer + "/openidm/managed/user/" + existingUserId + "?_prettyPrint=true";
    HTTPRequest request = new HTTPRequest(url, "GET", headers);
    HTTPResponse response = request.send();

    System.out.println("Requested " + request);
    System.out.println("Recieved " + response);
    assertEquals(200, response.code);
  }

  @Test(timeout = 10000)
  public void simple404Test() {
    HTTPRequestTest.prepareDatabase();
    Map<String, String> headers = new Hashtable<String, String>();
    headers.put("X-OpenIDM-Username", c.openIDMUsername);
    headers.put("X-OpenIDM-Password", c.openIDMPassword);
    headers.put("Content-Type", "application/json");
    String url = c.openIDMServer + "/openidm/managed/user/" + nonExistingUserId + "?_prettyPrint=true";
    HTTPRequest request = new HTTPRequest(url, "GET", headers);
    HTTPResponse response = request.send();

    System.out.println("Requested " + request);
    System.out.println("Recieved " + response);
    assertEquals(404, response.code);
  }

  @Test(timeout = 10000)
  public void dataTest() {
    HTTPRequestTest.prepareDatabase();
    Map<String, String> headers = new Hashtable<String, String>();
    headers.put("X-OpenIDM-Username", c.openIDMUsername);
    headers.put("X-OpenIDM-Password", c.openIDMPassword);
    headers.put("Content-Type", "application/json");
    String url = c.openIDMServer + "/openidm/managed/user/" + nonExistingUserId + "?_prettyPrint=true";
    HTTPRequest request = new HTTPRequest(url, "PUT", headers,
        "{\"givenName\":\"" + nonExistingUserId + "\",\"sn\":\"" + nonExistingUserId + "\",\"userName\":\""
            + nonExistingUserId + "\",\"_id\":\"" + nonExistingUserId + "\",\"mail\":\"NonExistingUser@ex.com\"}");
    HTTPResponse response = request.send();

    System.out.println("Requested " + request);
    System.out.println("Recieved " + response);
    assertEquals(201, response.code);

    HTTPRequest request2 = new HTTPRequest(url, "DELETE", headers);
    HTTPResponse response2 = request2.send();
    System.out.println("Requested " + request2);
    System.out.println("Recieved " + response2);
    assertEquals(200, response2.code);
  }
}
