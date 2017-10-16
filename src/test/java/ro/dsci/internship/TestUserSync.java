package ro.dsci.internship;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Joiner;

public class TestUserSync {
  String locatie = "src/test/resources/CVSTest.csv";
  String locatie2 = "target/CVSTest2.csv";

  @Test
  public void testGabiUserDao() {
    GabrielUserDao userSync = new GabrielUserDao();
    testWithSpecificUserSyncImplementation(userSync);
  }

  @Test
  public void testH2UserDao() {
    UnirestForgeRockUserDao forgerock = new UnirestForgeRockUserDao();
    UserDao dao = new H2UserDao();
    testExternalServerUserDao(dao);
  }

  @Test
  public void testEnd2EndUserDao() {
    UnirestForgeRockUserDao forgerock = new UnirestForgeRockUserDao();
    UserDao dao = new H2UserDao();
    List<User> users = forgerock.readUsers(locatie);
    dao.writeUsers(users, locatie);
  }

  @Test
  // sterge, citeste si scrie useri pe serveri

  public void testGabiForgerockUserDao() {
    UnirestForgeRockUserDao adminDao = new UnirestForgeRockUserDao();
    GabrielUserDao dao = new GabrielUserDao();

    List<User> usersServerInitial = adminDao.readUsers("");
    List<User> localUsers = dao.readUsers(locatie);
    adminDao.writeUsers(localUsers, "");

    List<User> usersServerFinal = adminDao.readUsers("");
    Assert.assertEquals(usersServerFinal.size(), localUsers.size() + usersServerInitial.size());

  }

  @Test
  public void testVladForgerockUserDao() {
    UserDao userSync = new UnirestForgeRockUserDao();
    UserDao localUser = new VladUserDao();
    List<User> useriLocali = localUser.readUsers(locatie);

    List<User> usersServerInit = userSync.readUsers("");
    userSync.writeUsers(useriLocali, "");
    List<User> usersServerFin = userSync.readUsers("");
    Assert.assertEquals(usersServerFin.size(), usersServerInit.size() + useriLocali.size());
    usersServerFin.forEach(System.out::println);
  }

  @Test
  public void testVladUserDao() {
    UserDao userSync = new VladUserDao();
    testWithSpecificUserSyncImplementation(userSync);
  }

  @Test
  public void testAbs() {
    System.out.println(Integer.MIN_VALUE);
    System.out.println(Math.abs(Integer.MIN_VALUE));
    System.out.println(Integer.MAX_VALUE);
    System.out.println(Math.abs(Integer.MAX_VALUE));
    System.out.println(System.currentTimeMillis());
  }

  @Test
  @Ignore("already implemented")
  public void testIoanaUserDao() {
    testWithSpecificUserSyncImplementation(new IoanaUserDao());
  }

  private void testWithSpecificUserSyncImplementation(UserDao dao) throws RuntimeException {
    List<User> users = dao.readUsers(locatie);
    Assert.assertEquals(4, users.size());
    Assert.assertEquals("firstuser@gmail.com", users.get(0).email);

    Path p1 = Paths.get(locatie2);
    try {
      Files.deleteIfExists(p1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Assert.assertFalse("everything ok", Files.exists(p1));

    dao.writeUsers(users, locatie2);
    boolean exists = Files.exists(p1);
    Assert.assertTrue("everything ok", exists);
    List<User> actual = dao.readUsers(locatie2);
    Assert.assertEquals(4, actual.size());

    Assert.assertEquals(users, actual);
  }

  /*private void testReadWrite(UserDao dao, int size) throws RuntimeException {
    List<User> users = dao.readUsers(locatie);
    System.out.println(Joiner.on("\n").join(users));
    Assert.assertEquals(size, users.size());
  }*/

  @Test
  public void testReadWrite() {
    UserDao userSync = new UnirestForgeRockUserDao();
    List<User> users = userSync.readUsers("");
    List<User> newUsers = Arrays.asList(new User("id1", "username", "first", "last", "test@gmail.com"));
    userSync.writeUsers(newUsers, "");
    List<User> users2 = userSync.readUsers("");
    Assert.assertEquals(users.size() + 1, users2.size());
    users.addAll(newUsers);
    Assert.assertEquals(users.toString(), users2.toString());
    Assert.assertEquals(users, users2);
  }

  @Test
  public void testEquals() {
    BigInteger a = new BigInteger("34242354352353425645475678678675676346563456321134523");
    BigInteger b = new BigInteger("34242354352353425645475678678675676346563456321134523");
    BigInteger actual = a.multiply(b);
    BigInteger expected = new BigInteger(
        "1172538831592137592283049939512687211197454925126862789967475671863286036654308225176012366710357862437529");
    //System.out.println("big=" + actual);
    Assert.assertFalse(expected == actual);
    Assert.assertEquals(expected.toString(), actual.toString());
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testStringEquals() {
    String a = "34242354352353425645475678678675676346563456321134523";
    String b = "5436867978654654675685679789078";
    String c = "5436867978654654675685679789078";
    String d = "5436867978654654675685679789078" + "";
    String actual = a + "/" + b;
    String expected = "34242354352353425645475678678675676346563456321134523/5436867978654654675685679789078";
    // System.out.println("big=" + actual);
    Assert.assertTrue(b == c);
    Assert.assertTrue(c == d);
    Assert.assertFalse(c + "/" == d + "/");
    Assert.assertEquals(c + "/", d + "/");
    Assert.assertFalse(expected == actual);
    Assert.assertEquals(expected.toString(), actual.toString());
    Assert.assertEquals(expected, actual);
  }
  //
  // private void testReadWrite(UserDao dao, int size) throws RuntimeException {
  // List<User> users = dao.readUsers(locatie);
  //
  // System.out.println(Joiner.on("\n").join(users));
  // Assert.assertEquals(size, users.size());
  // }

  @Test
  public void testReadWriteOnUirestForgeRockUserDao() {
    UnirestForgeRockUserDao adminDao = new UnirestForgeRockUserDao();
    testExternalServerUserDao(adminDao);
  }

  private void testExternalServerUserDao(UserDao dao) {
    List<User> usersServer = dao.readUsers("");
    List<User> newUsers = Arrays.asList(new User("id", "username", "first", "last", "email@gmail.com"));
    dao.writeUsers(newUsers, "");
    List<User> usersServer2 = dao.readUsers("");
    Assert.assertEquals(usersServer.size() + 1, usersServer2.size());
  }

  @Test
  public void testEqualsOnStrings() {
    String userId = "1";
    String user1 = "username" + "1";
    String user2 = "username" + userId;
    String user3 = "username1";
    Assert.assertTrue(user1 != user2);
    Assert.assertTrue(user1 == user3);
    Assert.assertTrue(user2 != user3);
  }

  @Test
  public void testEqualsOnUsers() {
    String userId = "1";
    User user1 = new User("02", "username" + "1", "first1", "last1", "email1");
    User user2 = new User("02", "username" + userId, "first1", "last1", "email1");
    User user3 = new User("02", "username1", "first1", "last1", "email1");
    Assert.assertEquals(user1.toString(), user2.toString());
    Assert.assertEquals(user1.toString(), user3.toString());
    Assert.assertEquals(user1, user2);
    Assert.assertEquals(user1, user3);
  }

  @Test
  public void testEqualsBetweenLists() {

    Assert.assertEquals(Arrays.asList("ab"), Arrays.asList("a" + "b"));

    User user1 = new User("02", "username1", "first1", "last1", "email1");
    User user2 = new User("21", "username2", "first2", "last2", "email2");
    List<User> l1 = Arrays.asList(user1, user2);
    List<User> l2 = Arrays.asList(user1, user2);
    Assert.assertEquals(l1, l2);
  }
}
