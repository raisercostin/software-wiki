package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static ro.dcsi.internship.OpenIdConfig.*;

public class UserSyncTest {
  @Test//(timeout=2000)
  public void twoIterators(){
    ForgeRockUserDao daoAdmin1 = new ForgeRockUserDao(IntegrationTestConfig.testInstance);
    UserDao dao1 = daoAdmin1;
    for (User u1 : dao1) {
      for (User u2 : dao1) {
        System.out.println(u1.username()+"-"+u2.username());
      }
    }
    assertEquals(6,daoAdmin1.requestsToServer());
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
