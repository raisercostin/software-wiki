package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;

public class ForgeRockDbTest {
  private static final String forgeRockHostname = "http://dcs-xps:8080";
  private static final String username = "openidm-admin";
  private static final String pass = "openidm-admin2";

  String existingUserId = "ForgeRockDBTestExistingUser";
  String nonExistingUserId = "ForgeRockDBTestNonExistingUser";

  @Test
  public void test() {
    /* TODO general tests */
    ForgeRockDB db = new ForgeRockDB(forgeRockHostname, username, pass);
    User user = db.getUser(existingUserId);
    assertNotNull(user);
    assertEquals(user.getAttributeValue("city"), "buch");
  }

  @Test
  public void userExistsTest() {
    /* TODO general tests */
    ForgeRockDB db = new ForgeRockDB(forgeRockHostname, username, pass);
    assertTrue(db.userExists(existingUserId));
    assertFalse(db.userExists(nonExistingUserId));
  }

  @Test
  public void deleteUserTest() {
    /* TODO general tests */
    ForgeRockDB db = new ForgeRockDB(forgeRockHostname, username, pass);
    assertTrue(db.deleteUser(existingUserId));
    assertFalse(db.deleteUser(nonExistingUserId));
  }

  @Test
  public void updateUserTest() {
    /* TODO general tests */
    Hashtable<String, String> attributes = new Hashtable<String, String>();
    attributes.put("_id", "Joe");
    attributes.put("mail", "joe@ex.com");
    User user = new User("Joe", attributes);
    ForgeRockDB db = new ForgeRockDB(forgeRockHostname, username, pass);
    db.updateUser(user);
  }
}
