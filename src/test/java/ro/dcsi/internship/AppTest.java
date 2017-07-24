package ro.dcsi.internship;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class AppTest {
  static String target = "target/test-files/";
  static String resources = "src/test/resources/";

  @Test
  public void testLiviu() {
    UserController controller = new UserController();
    List<TheUser> existingUsers = controller.readUsers(resources + "users.csv");
    Assert.assertEquals(8,existingUsers.size());
    
    TheUser[] users = new TheUser[existingUsers.size()];

    controller.writeUsers(target + "tempUsers", existingUsers.toArray(users));

    List<TheUser> tempUsers = controller.readUsers(target + "tempUsers");

    Assert.assertEquals(existingUsers.size(), tempUsers.size());

    for (int i = 0; i < existingUsers.size(); i++) {
      Assert.assertEquals(existingUsers.get(i).toString(), tempUsers.get(i).toString());
    }
  }

  @Test
  public void testReadAppSorin() {
    UserDao appS = new UserDaoSorin();
    System.out.println(appS.readUsers(target + "sorinUsersStyle.csv"));
  }

  @Test
  public void testAppSorin() {
    UserDao appS = new UserDaoSorin();
    appS.writeUsers(target + "newSorinUsersCsv.csv",
        new TheUser("ion12", "abc", "IonIon", 755, 22, "RO", "ion.ion@ionmail.com"),
        new TheUser("gigi123200", "qwerty", "GigelMasan", 753, 21, "RO", "gigi.ggg@gmail.com"));
    appS.writeUsers(target + "new2SorinUsersCsv2.csv",
        new TheUser("sorin", "mnqw12", "SorinDragan", 777, 20, "RO", "sorin.dragan27@gmail.com"));
    Assert.assertEquals(2, appS.readUsers(target + "newSorinUsersCsv.csv").size());
    Assert.assertEquals(1, appS.readUsers(target + "new2SorinUsersCsv2.csv").size());
  }

  @Test
  public void testAppIulian() {
    UserDao app = new UserDaoIulian();
    app.writeUsers(target + "file1", new TheUser("1"), new TheUser("1"));
    app.writeUsers(target + "file2", new TheUser("2"));
    Assert.assertEquals(2, app.readUsers(target + "file1").size());
    Assert.assertEquals(1, app.readUsers(target + "file2").size());
    Assert.assertEquals("2", app.readUsers(target + "file2").get(0).username);
  }

  @Test
  public void testGrigore() {
    UserDao app = new UserDaoGrigore();
    app.writeUsers(target+"file1", new TheUser("ion"), new TheUser("gigi"));
    app.writeUsers(target+"file2", new TheUser("costin"));

    List<TheUser> ls = app.readUsers(target+"file1");
    Assert.assertEquals(2, ls.size());
    Assert.assertEquals(1, app.readUsers(target+"file2").size());
  }

  @Test
  public void testReadGrigore() {
    UserDao app = new UserDaoGrigore();
    List<TheUser> ls = app.readUsers(resources + "users");
    Assert.assertEquals(9, ls.size());
    //8 if header is not read
  }

  @Test
  @Ignore
  public void testReadCostin() {
    UserDao app = new UserDaoCostin();
    List<TheUser> ls = app.readUsers("users");
    Assert.assertEquals(8, ls.size());
  }

  @Test(expected = NullPointerException.class)
  public void workingWithNull() {
    Integer a = null;
    System.out.println(a.toString());
    int c = a.intValue();
    // if(a != null )// is a a real object?
    Optional<Integer> b = Optional.empty();
    Integer d = c = b.get().intValue();
    d.intValue();
  }
}
