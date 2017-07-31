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

  static String target = "target/test-files/";

  @Test
  public void testBackup() {
    BeanBasedUserDao controller = new BeanBasedUserDao();
    List<TheUser> existingUsers = controller.readUsers(target + "tempUsers.csv");

    ForgerockUserDao forgerockUserDao = createDefaultServer();
    forgerockUserDao.backupUsers(existingUsers);
  }

  @Test
  public void testReadUsers() {
    ForgerockUserDao forgerockUserDao = createDefaultServer();
    List<TheUser> theUserList = forgerockUserDao.readUsers("");

    for (TheUser user : theUserList) {
      System.out.println(user.toString());
    }
  }

  @Test
  public void testWriteUsers() {
    ForgerockUserDao forgerockUserDao = createDefaultServer();
    forgerockUserDao.writeUsersToServer(0, generateUsers(3).toArray(new TheUser[0]));
    forgerockUserDao.writeUsersToServer(3, generateUsers(3).toArray(new TheUser[0]));
    forgerockUserDao.writeUsersToServer(6, generateUsers(3).toArray(new TheUser[0]));
  }

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
    forgerockUserDao.writeUsersToServer(0, generateUsers(users).toArray(new TheUser[0]));
    List<TheUser> theUserList = forgerockUserDao.readUsers("");
    forgerockUserDao.backupUsers(theUserList);
  }
  //TODO move outside. we test user not forge rock
  @Test(timeout=1000)
  public void testIdGeneration() {
    TheUser user = new UserBuilder().build();
    Assert.assertNotNull(user.id);
  }

  private ForgerockUserDao createDefaultServer() {
    return new ForgerockUserDao("http://dcs-xps:8080/","openidm-admin","openidm-admin");
  }

  public List<TheUser> generateUsers(int n) {
    List<TheUser> theUserList = new ArrayList<>();
    Faker faker = new Faker();
    for (int i = 0; i < n; i++) {
      TheUser user = new UserBuilder().
          setUsername("u"+i+" - " + faker.name().username()).setFirstName(faker.name().firstName())
          .setLastName(faker.name().lastName()).setEmail(faker.name().username() + "@gmail.com").build();
      theUserList.add(user);
    }
    return theUserList;
  }
}
