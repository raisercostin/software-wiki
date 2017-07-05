package ro.dcsi.internship;

import static org.junit.Assert.*;
import static ro.dcsi.internship.IntegrationTestConfig.testInstance;

import org.junit.Test;

public class UserSyncAppTest {
  @Test
  public void testBackup() {
    String csvFile = "target/UserSyncAppBackupTest.csv";
    String[] args = { "-dt", "csv", "-df", csvFile, "-st", "fr", "-ss",
        testInstance.openIDMServer, "-su", testInstance.openIDMUsername, "-sp",
        testInstance.openIDMPassword };
    UserSyncApp.main(args);
    UserDao src = new ForgeRockUserDao(testInstance);
    UserDao dest = new CsvUserDao(csvFile);
    for (User user : src) {
      assertTrue(dest.userExists(user.getId()));
    }
    for (User user : dest) {
      assertTrue(src.userExists(user.getId()));
    }
  }
  @Test
  public void testRestore() {
    String csvFile = "src/test/resources/CSV/UserSyncAppRestoreTest.csv";
    String[] args = { "-st", "csv", "-sf", csvFile, "-dt", "fr", "-ds",
        testInstance.openIDMServer, "-du", testInstance.openIDMUsername, "-dp",
        testInstance.openIDMPassword };
    UserSyncApp.main(args);
    UserDao src = new ForgeRockUserDao(testInstance);
    UserDao dest = new CsvUserDao(csvFile);
    for (User user : src) {
      assertTrue(dest.userExists(user.getId()));
    }
    for (User user : dest) {
      assertTrue(src.userExists(user.getId()));
    }
  }
}
