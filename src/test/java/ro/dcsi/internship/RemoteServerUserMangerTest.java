package ro.dcsi.internship;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class RemoteServerUserMangerTest {

  @Test
  // @Ignore("depends on local server")
  public void test() {
    // readUsers("localhost:8080");
    RemoteServerUserManager um = new RemoteServerUserManager();
    List<User> users = um.readUsers("dcs-xps:8080");
    for (User us : users)
      System.out.println(us);
    Assert.assertNotNull(users);
    Assert.assertEquals(2, users.size());
  }
}
