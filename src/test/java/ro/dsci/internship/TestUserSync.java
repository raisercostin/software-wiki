package ro.dsci.internship;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cvs.User;

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
  public void testInterface() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
    List<User> users = UserSync.readUsers(locatie);
    Assert.assertEquals(4, users.size());
    Assert.assertEquals("firstuser@gmail.com", users.get(0).email);
    System.out.println(Arrays.toString(users.getClass().getMethods()));
    users.getClass().getMethods()[0].invoke(users, null);
  }
}
