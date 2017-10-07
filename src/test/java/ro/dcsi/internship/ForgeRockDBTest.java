package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Test;

public class ForgeRockDBTest {
  /* TODO add required field checks */
  private static String existingUserId = "ForgeRockDBTestExistingUser";
  private static String nonExistingUserId = "ForgeRockDBTestNonExistingUser";

  @AfterClass
  public static void prepareDatabase() {
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);
    db.deleteUser(existingUserId);
    db.deleteUser(nonExistingUserId);
    Hashtable<String, String> existingUserAttributes = new Hashtable<String, String>();
    existingUserAttributes.put("_id", existingUserId);
    existingUserAttributes.put("userName", existingUserId);
    existingUserAttributes.put("mail", "ExistingUser@ex.com");
    existingUserAttributes.put("sn", existingUserId);
    existingUserAttributes.put("givenName", existingUserId);
    ExtendedUser existingUser = new ExtendedUser(existingUserId, existingUserAttributes);
    db.addUser(existingUser);
  }

  @Test(timeout = 10000)
  public void prepareDatabaseTest() {
    ForgeRockDBTest.prepareDatabase();
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);
    Optional<ExtendedUser> user = db.getUser(existingUserId);
    assertTrue(user.isPresent());
    assertEquals(existingUserId, db.getUser(existingUserId).get().getId());
  }

  @Test(timeout = 10000)
  public void getUserTest() {
    ForgeRockDBTest.prepareDatabase();
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);

    // get an existing user
    Optional<ExtendedUser> user = db.getUser(existingUserId);
    assertTrue(user.isPresent());
    assertEquals(existingUserId, user.get().getAttributeValue("sn"));

    // get a non existing user
    Optional<ExtendedUser> user2 = db.getUser(nonExistingUserId);
    assertFalse(user2.isPresent());
  }

  @Test(timeout = 10000)
  public void userExistsTest() {
    ForgeRockDBTest.prepareDatabase();
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);
    assertTrue(db.userExists(existingUserId));
    assertFalse(db.userExists(nonExistingUserId));
  }

  @Test(timeout = 10000)
  public void deleteUserTest() {
    ForgeRockDBTest.prepareDatabase();
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);
    assertTrue(db.deleteUser(existingUserId));
    assertFalse(db.deleteUser(nonExistingUserId));
  }

  @Test(timeout = 10000)
  public void updateUserTest() {
    ForgeRockDBTest.prepareDatabase();
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);

    // update an existing user
    Hashtable<String, String> attributes = new Hashtable<String, String>();
    attributes.put("_id", existingUserId);
    attributes.put("mail", "ExistingUser@ex.com");
    attributes.put("sn", existingUserId);
    attributes.put("givenName", existingUserId);
    attributes.put("userName", "joe");
    attributes.put("city", "Bucharest");
    attributes.put("customAttr", "customVal");
    ExtendedUser user = new ExtendedUser(existingUserId, attributes);
    assertTrue(db.updateUser(user));
    assertTrue(db.getUser(existingUserId).isPresent());
    assertEquals("joe", db.getUser(existingUserId).get().getAttributeValue("userName"));
    assertEquals("Bucharest", db.getUser(existingUserId).get().getAttributeValue("city"));
    assertEquals("customVal", db.getUser(existingUserId).get().getAttributeValue("customAttr"));

    // update a non existing user
    Hashtable<String, String> attributes2 = new Hashtable<String, String>();
    attributes.put("_id", nonExistingUserId);
    attributes.put("mail", "NonExistingUser@ex.com");
    attributes.put("sn", nonExistingUserId);
    attributes.put("givenName", nonExistingUserId);
    attributes.put("userName", "joe");
    attributes.put("city", "Bucharest");
    attributes.put("customAttr", "customVal");
    ExtendedUser user2 = new ExtendedUser(nonExistingUserId, attributes2);
    assertFalse(db.updateUser(user2));
  }

  @Test(timeout = 10000)
  public void addUserTest() {
    ForgeRockDBTest.prepareDatabase();
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);

    // add a non existing user
    Hashtable<String, String> attributes = new Hashtable<String, String>();
    attributes.put("_id", nonExistingUserId);
    attributes.put("mail", "NonExistingUser@ex.com");
    attributes.put("sn", nonExistingUserId);
    attributes.put("givenName", nonExistingUserId);
    attributes.put("userName", nonExistingUserId);
    ExtendedUser user = new ExtendedUser(nonExistingUserId, attributes);
    assertTrue(db.addUser(user));
    assertTrue(db.getUser(nonExistingUserId).isPresent());
    assertEquals(nonExistingUserId, db.getUser(nonExistingUserId).get().getAttributeValue("userName"));

    // add an existing user
    Hashtable<String, String> attributes2 = new Hashtable<String, String>();
    attributes2.put("_id", existingUserId);
    attributes2.put("mail", "ExistingUser@ex.com");
    attributes2.put("sn", existingUserId);
    attributes2.put("givenName", existingUserId);
    attributes2.put("userName", "joe");
    ExtendedUser user2 = new ExtendedUser(existingUserId, attributes2);
    assertFalse(db.addUser(user2));
    assertTrue(db.getUser(existingUserId).isPresent());
    assertEquals(existingUserId, db.getUser(existingUserId).get().getAttributeValue("userName"));
  }
}
