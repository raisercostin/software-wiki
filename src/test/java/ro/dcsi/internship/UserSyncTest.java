package ro.dcsi.internship;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import static ro.dcsi.internship.OpenIdConfig.*;

public class UserSyncTest {
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
