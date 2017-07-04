package ro.dcsi.internship;

import static org.junit.Assert.*;
import java.util.Hashtable;
import org.junit.AfterClass;
import org.junit.Test;
import static ro.dcsi.internship.IntegrationTestConfig.*;

public class ForgeRockDBIteratorTest {
  private static String existingUserId = "ForgeRockDBIteratorTestExistingUser";
  private static String nonExistingUserId = "ForgeRockDBIteratorTestNonExistingUser";

  @AfterClass
  public static void prepareDatabase() {
    ForgeRockUserDao db = new ForgeRockUserDao(testInstance);
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

  @Test(timeout = 6000)
  public void simpleIteratorTest() {
    ForgeRockDBIteratorTest.prepareDatabase();
    ForgeRockUserDao db = new ForgeRockUserDao(testInstance);
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
