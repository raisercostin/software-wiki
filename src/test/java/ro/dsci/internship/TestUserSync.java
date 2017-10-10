package ro.dsci.internship;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

  @Test(timeout = 1000)
  //sterge,  citeste  si scrie useri pe serveri
  public void testGabiForgerockUserDao() {
    GabrielForgerockUserDao adminDao = new GabrielForgerockUserDao();
    UserDao dao = adminDao;
    adminDao.deleteAllEntries();
    List<User> localUsers = new GabrielUserDao().readUsers(locatie);
    List<User> initialUsersOnServer = dao.readUsers("");
    Assert.assertTrue(initialUsersOnServer.isEmpty());

    dao.writeUsers(localUsers, "");
    List<User> usersServerFinal = dao.readUsers("");
    Assert.assertEquals(usersServerFinal, localUsers);

  }

  @Test
  public void testVladForgerockUserDao() {
    UserDao userSync = new VladForgeRockUserDao();
    UserDao localUser = new VladUserDao();
    List<User> useriLocali = localUser.readUsers(locatie);

    List<User> usersServerInit = userSync.readUsers("");
    userSync.writeUsers(useriLocali, "");
    List<User> usersServerFin = userSync.readUsers("");
    Assert.assertEquals(usersServerFin.size(), usersServerInit.size() + useriLocali.size());

  }
  //
  //  @Test
  //  public void testUnirestForgerockUserDao() {
  //    UserDao userSync = new UnirestForgeRockUserDao();
  //    testReadWrite(userSync, 1);
  //  }

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
    Assert.assertEquals(users.toString(), actual.toString());
    Assert.assertEquals(users, actual);
  }

  @Test
  public void testEquals() {
    BigInteger a = new BigInteger("34242354352353425645475678678675676346563456321134523");
    BigInteger b = new BigInteger("34242354352353425645475678678675676346563456321134523");
    BigInteger actual = a.multiply(b);
    BigInteger expected = new BigInteger(
        "1172538831592137592283049939512687211197454925126862789967475671863286036654308225176012366710357862437529");
    System.out.println("big=" + actual);
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
    System.out.println("big=" + actual);
    Assert.assertTrue(b == c);
    Assert.assertTrue(c == d);
    Assert.assertFalse(c + "/" == d + "/");
    Assert.assertEquals(c + "/", d + "/");
    Assert.assertFalse(expected == actual);
    Assert.assertEquals(expected.toString(), actual.toString());
    Assert.assertEquals(expected, actual);
  }
  //
  //  private void testReadWrite(UserDao dao, int size) throws RuntimeException {
  //    List<User> users = dao.readUsers(locatie);
  //
  //    System.out.println(Joiner.on("\n").join(users));
  //    Assert.assertEquals(size, users.size());
  //  }

  @Test
  public void testReadWriteOnUirestForgeRockUserDao() {
    UserDao userSync = new UnirestForgeRockUserDao();
    List<User> users = userSync.readUsers("");
    List<User> newUsers = Arrays.asList(new User("88", "username", "first", "last", "email@gmail.com"));
    userSync.writeUsers(newUsers, "");
    List<User> users2 = userSync.readUsers("");
    Assert.assertEquals(users.size() + 1, users2.size());
    users.addAll(newUsers);
    Assert.assertEquals(users, users2);
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
    User user1 = new User("02", "username1", "first1", "last1", "email1");
    User user2 = new User("21", "username2", "first2", "last2", "email2");
    List<User> l1 = Arrays.asList(user1, user2);
    List<User> l2 = Arrays.asList(user1, user2);
    Assert.assertEquals(l1, l2);
  }
}
