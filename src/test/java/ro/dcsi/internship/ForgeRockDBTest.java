package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;

public class ForgeRockDBTest {
  String existingUserId = "ForgeRockDBTestExistingUser", nonExistingUserId = "ForgeRockDBTestNonExistingUser";

  @Test
  public void test() {
    /* TODO general tests */
    ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
    User user = db.getUser(existingUserId);
    assertNotNull(user);
    assertEquals(user.getAttributeValue("city"), "buch");
  }

  @Test
  public void userExistsTest() {
    /* TODO general tests */
    ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
    assertTrue(db.userExists(existingUserId));
    assertFalse(db.userExists(nonExistingUserId));
  }

  @Test
  public void deleteUserTest() {
    /* TODO general tests */
    ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
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
    ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
    db.updateUser(user);
  }
}
