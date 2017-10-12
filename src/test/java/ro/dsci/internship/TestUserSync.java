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
import com.google.common.collect.Collections2;

<<<<<<< HEAD
public class TestUserSync {
  String locatie = "src/test/resources/CVSTest.csv";
  String locatie2 = "target/CVSTest2.csv";
=======
import ch.qos.logback.core.net.SyslogOutputStream;

public class TestUserSync {
  String locatie = "src/test/resources/CVSTest.csv";
  String locatie2 = "target/CVSTest2.csv";
  
>>>>>>> 33b26c02a1a6dd353cefe40126e92530d06848e2

  @Test
  public void testGabiUserDao() {
    GabrielUserDao userSync = new GabrielUserDao();
    testWithSpecificUserSyncImplementation(userSync);
  }

  @Test
  // sterge, citeste si scrie useri pe serveri
  
  public void testGabiForgerockUserDao() {
    UnirestForgeRockUserDao adminDao = new UnirestForgeRockUserDao();
    GabrielUserDao dao = new GabrielUserDao();
    
    List<User> usersServerInitial=adminDao.readUsers("");
    List<User> localUsers = dao.readUsers(locatie);
    adminDao.writeUsers(localUsers, "");
    
    List<User> usersServerFinal = adminDao.readUsers("");
    Assert.assertEquals(usersServerFinal.size(), localUsers.size()+usersServerInitial.size());

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
<<<<<<< HEAD

  @Test
  public void testUnirestForgerockUserDao() {
    UserDao userSync = new UnirestForgeRockUserDao();
    testReadWrite(userSync, 617);
  }

  @Test
  public void testVladUserDao() {
    testWithSpecificUserSyncImplementation(new VladUserDao());
  }

  @Test
=======
  //
  // @Test
  // public void testUnirestForgerockUserDao() {
  // UserDao userSync = new UnirestForgeRockUserDao();
  // testReadWrite(userSync, 1);
  // }

  @Test
  public void testVladUserDao() {
    UserDao userSync = new VladUserDao();
    testWithSpecificUserSyncImplementation(userSync);
  }

  @Test
>>>>>>> 33b26c02a1a6dd353cefe40126e92530d06848e2
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
<<<<<<< HEAD

    Assert.assertEquals(users, actual);
  }

  private void testReadWrite(UserDao dao, int size) throws RuntimeException {
    List<User> users = dao.readUsers(locatie);
    System.out.println(Joiner.on("\n").join(users));
    Assert.assertEquals(size, users.size());
  }

  @Test
  public void testReadWrite() {
    UserDao userSync = new UnirestForgeRockUserDao();
    List<User> users = userSync.readUsers("");
    List<User> newUsers = Arrays.asList(new User("id1", "first", "last", "email"));
    userSync.writeUsers(newUsers, "");
    List<User> users2 = userSync.readUsers("");
    Assert.assertEquals(users.size() + 1, users2.size());
    users.addAll(newUsers);
    Assert.assertEquals(users, users2);
=======
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
    List<User> usersServer = adminDao.readUsers("");
   
    List<User> newUsers = Arrays.asList(new User("id", "username", "first", "last", "email@gmail.com"));
    adminDao.writeUsers(newUsers, "");
    
    List<User> usersServer2 = adminDao.readUsers("");
    
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
>>>>>>> 33b26c02a1a6dd353cefe40126e92530d06848e2
  }

  @Test
  public void testEqualsBetweenLists() {
<<<<<<< HEAD
    Assert.assertEquals(Arrays.asList("ab"), Arrays.asList("a" + "b"));
=======
    User user1 = new User("02", "username1", "first1", "last1", "email1");
    User user2 = new User("21", "username2", "first2", "last2", "email2");
    List<User> l1 = Arrays.asList(user1, user2);
    List<User> l2 = Arrays.asList(user1, user2);
    Assert.assertEquals(l1, l2);
>>>>>>> 33b26c02a1a6dd353cefe40126e92530d06848e2
  }
}