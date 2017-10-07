package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.github.javafaker.Faker;

/**
 * Created by Cristi on 27-Jul-17.
 */
public class ForgerockTest {

  private static String target = "target/test-files/";

  @Test
  public void testBackup() {
    BeanBasedUserDao controller = new BeanBasedUserDao();
    List<User> existingUsers = controller.readUsers(target + "tempUsers.csv");

    ForgerockUserDao forgerockUserDao = createDefaultServer();
    forgerockUserDao.backupUsers(existingUsers);
  }

  @Test
  public void testReadUsers() {
    ForgerockUserDao forgerockUserDao = createDefaultServer();
    List<User> theUserList = forgerockUserDao.load();

    for (User user : theUserList) {
      System.out.println(user.toString());
    }
  }

  //  @Test
  //  public void testWriteUsers() {
  //    ForgerockUserDao forgerockUserDao = createDefaultServer();
  //    forgerockUserDao.writeUsers(generateUsers(3).toArray(new TheUser[0]));
  //    forgerockUserDao.writeUsers(generateUsers(3).toArray(new TheUser[0]));
  //    forgerockUserDao.writeUsers(generateUsers(3).toArray(new TheUser[0]));
  //  }

  @Test
  public void mainTest() {
    testManyUsers(10);
  }

  @Test
  @Ignore
  public void mainPerformanceIntegrationTest() {
    testManyUsers(100);
  }

  private void testManyUsers(int users) {
    ForgerockUserDao forgerockUserDao = createDefaultServer();
    forgerockUserDao.save(generateUsers(users).toArray(new User[0]));
    List<User> theUserList = forgerockUserDao.load();
    forgerockUserDao.backupUsers(theUserList);
  }

  //TODO move outside. we test user not forge rock
  @Test(timeout = 1000)
  public void testIdGeneration() {
    User user = new UserBuilder().build();
    Assert.assertNotNull(user.id);
  }

  private ForgerockUserDao createDefaultServer() {
    return new ForgerockUserDao("http://dcs-xps:8080/", "openidm-admin", "openidm-admin");
  }

  public List<User> generateUsers(int n) {
    List<User> theUserList = new ArrayList<>();
    Faker faker = new Faker();
    for (int i = 0; i < n; i++) {
      User user = new UserBuilder().setUsername("u" + i + " - " + faker.name().username())
          .setFirstName(faker.name().firstName()).setLastName(faker.name().lastName())
          .setEmail(faker.name().username() + "@gmail.com").build();
      theUserList.add(user);
    }
    return theUserList;
  }
}
