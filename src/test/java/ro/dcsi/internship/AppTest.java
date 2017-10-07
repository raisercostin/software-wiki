package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.raisercostin.jedi.Locations;

import com.github.javafaker.Faker;

public class AppTest {
  private static String target = "target/test-files/";
  private static String resources = "src/test/resources/";

  @Test
  public void testHeaderDefinesTheColumn() {
    BeanBasedUserDao app = new BeanBasedUserDao();
    Assert.assertEquals("Ambrose", app.readUsers(resources + "file-header1.csv").get(2).getLastname());
    Assert.assertEquals("Ambrose", app.readUsers(resources + "file-header2.csv").get(2).getLastname());
  }

  @Test
  public void testHeaderDefinesTheColumnDaoSorin() {
    BeanBasedUserDao app = new BeanBasedUserDao();
    Assert.assertEquals("Ambrose", app.readUsers(resources + "file-header1.csv").get(2).getLastname());
    Assert.assertEquals("Ambrose", app.readUsers(resources + "file-header2.csv").get(2).getLastname());
  }

  @Test
  public void testAppIulian() {
    BeanBasedUserDao app = new BeanBasedUserDao();
    app.writeUsers(target + "file1", new User("1"), new User("1"));
    app.writeUsers(target + "file2", new User("2"));
    Assert.assertEquals(2, app.readUsers(target + "file1").size());
    Assert.assertEquals(1, app.readUsers(target + "file2").size());
    Assert.assertEquals("2", app.readUsers(target + "file2").get(0).username);
  }

  @Test
  public void testLiviu() {
    BeanBasedUserDao controller = new BeanBasedUserDao();
    List<User> existingUsers = controller.readUsers(resources + "users.csv");
    Assert.assertEquals(8, existingUsers.size());

    User[] users = new User[existingUsers.size()];
    System.out.println(Locations.current(target + "tempUsers.csv").absolute());
    //File fisier = new File(Locations.current(target + "tempUsers.csv").mkdirOnParentIfNecessary().absolute());
    controller.writeUsers(target + "tempUsers.csv", existingUsers.toArray(users));

    List<User> tempUsers = controller.readUsers(target + "tempUsers.csv");

    Assert.assertEquals(existingUsers.size(), tempUsers.size());

    for (int i = 0; i < existingUsers.size(); i++) {
      Assert.assertEquals(existingUsers.get(i).toString(), tempUsers.get(i).toString());
    }
  }

  @Test
  public void testReadAppSorin() {
    BeanBasedUserDao appS = new BeanBasedUserDao();
    System.out.println(appS.readUsers(resources + "sorinUsersStyle.csv"));
  }

  public int getSize(String file) {
    return new BeanBasedUserDao().readUsers(file).size();

  }

  @Test
  public void testGrigore() {
    BeanBasedUserDao app = new BeanBasedUserDao();
    app.writeUsers(target + "file1", generateUsers(5).toArray(new User[0]));
    app.writeUsers(target + "file2", new User("costin"));

    List<User> ls = app.readUsers(target + "file1");
    Assert.assertEquals(5, ls.size());
    Assert.assertEquals(1, app.readUsers(target + "file2").size());
  }

  @Test
  @Ignore
  public void testReadCostin() {
    BeanBasedUserDao app = new BeanBasedUserDao();
    List<User> ls = app.readUsers("users");
    Assert.assertEquals(8, ls.size());
  }

  @SuppressWarnings("null")
  @Test(expected = NullPointerException.class)
  public void workingWithNull() {
    Integer a = null;
    try {
      System.out.println(a.toString());
    } catch (NullPointerException e) {
      Assert.assertTrue("exception was thrown", true);
    }
    @SuppressWarnings("unused")
    int c = a.intValue();
    // if(a != null )// is a a real object?
    Optional<Integer> b = Optional.empty();
    Integer d = c = b.get().intValue();
    d.intValue();
  }

  @Test
  public void simpleExceptionThrowing() {
    Integer a = null;
    try {
      someMethodThatShouldThrowAnExceptionIfParameterIsNull(a);
      Assert.fail("Nu e bine. Metoda a continuat normal.");
    } catch (NullPointerException e) {
      Assert.assertTrue("exception was thrown", true);
    }
  }

  private void someMethodThatShouldThrowAnExceptionIfParameterIsNull(Integer a) {
    System.out.println(a.toString());
  }

  public List<User> generateUsers(int n) {
    List<User> theUserList = new ArrayList<>();
    Faker faker = new Faker();
    for (int i = 0; i < n; i++) {
      Integer permission = (Math.random() < 0.5) ? 0 : 1;
      Integer age = faker.number().numberBetween(0, 100);
      User user = new UserBuilder().setUsername(faker.name().username()).setPasswd(faker.idNumber().valid())
          .setPermissions(permission).setAge(age).setId(faker.address().country())
          .setEmail(faker.name().username() + "@gmail.com").build();
      theUserList.add(user);
    }
    return theUserList;
  }
}
