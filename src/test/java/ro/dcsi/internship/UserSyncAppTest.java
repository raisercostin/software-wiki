package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ro.dcsi.internship.IntegrationTestConfig.testInstance;

import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class UserSyncAppTest {
  @Test
  public void twoSyncViaCommandLine1() {
    OpenIdConfig c = IntegrationTestConfig.testInstance;
    String csvFile = "target/export2.csv";
    String args[] = { "--forgerock", c.openIDMServer, c.openIDMUsername, c.openIDMPassword, "--csv",
        "target/export2.csv" };
    UserSyncApp.main(args);
    UserDao fr = new ForgeRockUserDao(c);
    UserDao csv = new CsvUserDao(csvFile);
    assertEverythingFromSrcIsInDst(fr,csv);
  }

  @Test
  public void twoSyncViaCommandLine2() {
    OpenIdConfig c = IntegrationTestConfig.testInstance;
    String srcFile = "src/test/resources/CSV/UserSyncAppRestoreTest.csv";

    String args[] = { "copy", "--csv", srcFile, "--forgerock", c.openIDMServer, c.openIDMUsername,
        c.openIDMPassword };
    UserSyncApp.main(args);
    UserDao fr = new ForgeRockUserDao(c);
    UserDao csv = new CsvUserDao(srcFile);
    assertEverythingFromSrcIsInDst(csv,fr);
  }

  private void assertEverythingFromSrcIsInDst(UserDao src, UserDao dest) {
    for (User user : src) {
      assertTrue("Search for user "+user.username()+" with id "+user.getId(),dest.userExists(user.getId()));
    }
  }

  @Test
  public void twoSync() {
    UserSync us = new UserSync();
    us.copyUsers(new ForgeRockUserDao(IntegrationTestConfig.testInstance), new CsvUserDao("target/export1.csv"));
  }

  @Test
  public void twoInMemory() {
    UserDao dao1 = new InbMemoryUserDao();
  }

  @Test // (timeout=2000)
  @Ignore("Cache not implemented yet")
  public void twoIterators() {
    ForgeRockUserDao daoAdmin1 = new ForgeRockUserDao(IntegrationTestConfig.testInstance);
    UserDao dao1 = daoAdmin1;
    for (User u1 : dao1) {
      for (User u2 : dao1) {
        System.out.println(u1.username() + "-" + u2.username());
      }
    }
    assertEquals(6, daoAdmin1.requestsToServer());
  }

  @Test
  @Ignore("Cache not implemented yet")
  public void callsToserver() {
    ForgeRockUserDao daoAdmin1 = new ForgeRockUserDao(IntegrationTestConfig.testInstance);
    assertEquals(0, daoAdmin1.requestsToServer());
    Iterator<User> i = daoAdmin1.read();
    assertEquals(1, daoAdmin1.requestsToServer());
    i.hasNext();
    assertEquals(2, daoAdmin1.requestsToServer());
    i.next();
    assertEquals(3, daoAdmin1.requestsToServer());
  }

  @Test
  public void csvBackupTest() {
    UserWriter exporter = new CsvUserDao("target/csvBackupTest.csv");
    ForgeRockUserDao db = new ForgeRockUserDao(IntegrationTestConfig.testInstance);

    exporter.write(db.iterator());
  }

  @Test
  public void csvRestoreTest() {
    CsvReader reader = new CsvReader("src/test/resources/CSV/csvRestoreTest.csv", ',');
    List<User> users = reader.readUsers();
    ForgeRockUserDao db = new ForgeRockUserDao(IntegrationTestConfig.testInstance);

    for (User user : users) {
      db.deleteUser(user.getId());
    }
    for (User user : users) {
      assertFalse(db.userExists(user.getId()));
    }
    for (User user : users) {
      db.addUser(user);
    }
    for (User user : users) {
      User user2 = db.getUser(user.getId()).get();
      for (String attr : user2.getAttributeSet()) {
        if (!attr.equals("_rev")) {
          assertEquals(user.getAttributeValue(attr), user2.getAttributeValue(attr));
        }
      }
      for (String attr : user.getAttributeSet()) {
        if (!attr.equals("_rev")) {
          assertEquals(user.getAttributeValue(attr), user2.getAttributeValue(attr));
        }
      }
    }
  }
}
