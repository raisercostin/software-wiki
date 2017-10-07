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
    IterableUserDao fr = new ExtendedForgeRockUserDao(c);
    IterableUserDao csv = new ExtendedCsvUserDao(csvFile);
    assertEverythingFromSrcIsInDst(fr,csv);
  }

  @Test
  public void twoSyncViaCommandLine2() {
    OpenIdConfig c = IntegrationTestConfig.testInstance;
    String srcFile = "src/test/resources/CSV/UserSyncAppRestoreTest.csv";

    String args[] = { "copy", "--csv", srcFile, "--forgerock", c.openIDMServer, c.openIDMUsername,
        c.openIDMPassword };
    UserSyncApp.main(args);
    IterableUserDao fr = new ExtendedForgeRockUserDao(c);
    IterableUserDao csv = new ExtendedCsvUserDao(srcFile);
    assertEverythingFromSrcIsInDst(csv,fr);
  }

  private void assertEverythingFromSrcIsInDst(IterableUserDao src, IterableUserDao dest) {
    for (ExtendedUser user : src) {
      assertTrue("Search for user "+user.username()+" with id "+user.getId(),dest.userExists(user.getId()));
    }
  }

  @Test
  public void twoSync() {
    UserSync us = new UserSync();
    us.copyUsers(new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance), new ExtendedCsvUserDao("target/export1.csv"));
  }

  @Test
  public void twoInMemory() {
    IterableUserDao dao1 = new InbMemoryUserDao();
  }

  @Test // (timeout=2000)
  @Ignore("Cache not implemented yet")
  public void twoIterators() {
    ExtendedForgeRockUserDao daoAdmin1 = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);
    IterableUserDao dao1 = daoAdmin1;
    for (ExtendedUser u1 : dao1) {
      for (ExtendedUser u2 : dao1) {
        System.out.println(u1.username() + "-" + u2.username());
      }
    }
    assertEquals(6, daoAdmin1.requestsToServer());
  }

  @Test
  @Ignore("Cache not implemented yet")
  public void callsToserver() {
    ExtendedForgeRockUserDao daoAdmin1 = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);
    assertEquals(0, daoAdmin1.requestsToServer());
    Iterator<ExtendedUser> i = daoAdmin1.read();
    assertEquals(1, daoAdmin1.requestsToServer());
    i.hasNext();
    assertEquals(2, daoAdmin1.requestsToServer());
    i.next();
    assertEquals(3, daoAdmin1.requestsToServer());
  }

  @Test
  public void csvBackupTest() {
    UserWriter exporter = new ExtendedCsvUserDao("target/csvBackupTest.csv");
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);

    exporter.write(db.iterator());
  }

  @Test
  public void csvRestoreTest() {
    CsvReader reader = new CsvReader("src/test/resources/CSV/csvRestoreTest.csv", ',');
    List<ExtendedUser> users = reader.readUsers();
    ExtendedForgeRockUserDao db = new ExtendedForgeRockUserDao(IntegrationTestConfig.testInstance);

    for (ExtendedUser user : users) {
      db.deleteUser(user.getId());
    }
    for (ExtendedUser user : users) {
      assertFalse(db.userExists(user.getId()));
    }
    for (ExtendedUser user : users) {
      db.addUser(user);
    }
    for (ExtendedUser user : users) {
      ExtendedUser user2 = db.getUser(user.getId()).get();
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
