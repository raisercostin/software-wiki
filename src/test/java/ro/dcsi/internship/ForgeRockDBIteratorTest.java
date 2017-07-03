package ro.dcsi.internship;

import static org.junit.Assert.*;
import java.util.Hashtable;
import org.junit.AfterClass;
import org.junit.Test;

public class ForgeRockDBIteratorTest {
  private static String existingUserId = "ForgeRockDBIteratorTestExistingUser";
  private static String nonExistingUserId = "ForgeRockDBIteratorTestNonExistingUser";
  private static String openIDMServer = "http://localhost:8080";
  private static String openIDMUsername = "openidm-admin";
  private static String openIDMPassword = "openidm-admin";

  @AfterClass
  public static void prepareDatabase() {
    ForgeRockDB db = new ForgeRockDB(openIDMServer, openIDMUsername, openIDMPassword);
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
  
  @Test
  public void simpleIteratorTest() {
    ForgeRockDBIteratorTest.prepareDatabase();
    ForgeRockDB db = new ForgeRockDB(openIDMServer, openIDMUsername, openIDMPassword);
    User found = null;
    for (User user : db) {
      if (user.getId().equals(existingUserId)) {
        found = user;
      }
    }
    assertNotNull(found);
    assertEquals(existingUserId, found.getAttributeValue("sn"));
  }
}
