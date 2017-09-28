package ro.dsci.internship;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class TestUserSync {
  String locatie = "src/test/resources/CVSTest.csv";
  String locatie2 = "src/test/resources/CVSTest2.csv";

  @Test
  public void testeaza() {
    CVS.citesteCVS(locatie);
    CVS.scrieCVS(locatie2);
    Path p1 = Paths.get(locatie2);
    boolean exists = Files.exists(p1);
    Assert.assertTrue("everything ok", exists);
  }

  @Test
  public void testGabiUserDao(){
    UserSync userSync = new UserSync();
    testWithSpecificUserSyncImplementation(userSync);
  }

  @Test
  public void testVladUserDao(){
    testWithSpecificUserSyncImplementation(new Tester1());
  }

  @Test
  public void testIoanaUserDao(){
    testWithSpecificUserSyncImplementation(new User(null,null,null));
  }

  private void testWithSpecificUserSyncImplementation(UserDao userSync) {
    List<User> users = userSync.readUsers(locatie);
    Assert.assertEquals(5, users.size());
    Assert.assertEquals("firstuser@gmail.com", users.get(0).email);
  }
}
