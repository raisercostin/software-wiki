package cvs;

import java.nio.file.*;
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
  public void testInterface() {
    List<User> users = UserSync.readUsers(locatie);
    Assert.assertEquals(4, users.size());
    Assert.assertEquals("firstuser@gmail.com", users.get(0).getEmail());
  }
}
