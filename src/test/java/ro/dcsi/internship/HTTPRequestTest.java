package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Test;

public class HTTPRequestTest {
  private static String existingUserId = "HTTPRequestTestExistingUser";
  private static String nonExistingUserId = "HTTPRequestTestNonExistingUser";
  private static String openIdmServer = "http://localhost:8080";
  private static String openIdmUsername = "openidm-admin";
  private static String openIdmPassword = "openidm-admin";

  @AfterClass
  public static void prepareDatabase() {
    ForgeRockDb db = new ForgeRockDb(openIdmServer, openIdmUsername, openIdmPassword);
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

  @Test
  public void prepareDatabaseTest() {
    /* TODO general tests */
    HTTPRequestTest.prepareDatabase();
    ForgeRockDb db = new ForgeRockDb(openIdmServer, openIdmUsername, openIdmPassword);
    Optional<User> user = db.getUser(existingUserId);
    assertTrue(user.isPresent());
    assertEquals(existingUserId, db.getUser(existingUserId).get().getId());
  }

  @Test
  public void simpleTest() {
    /* TODO general tests */
    HTTPRequestTest.prepareDatabase();
    Map<String, String> headers = new Hashtable<String, String>();
    headers.put("X-OpenIDM-Username", "openidm-admin");
    headers.put("X-OpenIDM-Password", "openidm-admin");
    headers.put("Content-Type", "application/json");
    String url = "http://localhost:8080/openidm/managed/user/" + existingUserId + "?_prettyPrint=true";
    HTTPRequest request = new HTTPRequest(url, "GET", headers);
    HTTPResponse response = request.send();

    System.out.println("Requested " + request);
    System.out.println("Recieved " + response);
    assertEquals(200, response.code);
  }

  @Test
  public void simple404Test() {
    /* TODO general tests */
    HTTPRequestTest.prepareDatabase();
    Map<String, String> headers = new Hashtable<String, String>();
    headers.put("X-OpenIDM-Username", "openidm-admin");
    headers.put("X-OpenIDM-Password", "openidm-admin");
    headers.put("Content-Type", "application/json");
    String url = "http://localhost:8080/openidm/managed/user/" + nonExistingUserId + "?_prettyPrint=true";
    HTTPRequest request = new HTTPRequest(url, "GET", headers);
    HTTPResponse response = request.send();

    System.out.println("Requested " + request);
    System.out.println("Recieved " + response);
    assertEquals(404, response.code);
  }

  @Test
  public void dataTest() {
    /* TODO general tests */
    HTTPRequestTest.prepareDatabase();
    Map<String, String> headers = new Hashtable<String, String>();
    headers.put("X-OpenIDM-Username", "openidm-admin");
    headers.put("X-OpenIDM-Password", "openidm-admin");
    headers.put("Content-Type", "application/json");
    String url = "http://localhost:8080/openidm/managed/user/" + nonExistingUserId + "?_prettyPrint=true";
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