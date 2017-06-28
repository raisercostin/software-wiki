package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Test;

public class UserSyncTest {

  @Test
  public void test() {
    Iterator<User> users = new FileUserManager2().readUsers("src/test/resources/users1.csv");
    assertNotNull(users);
    // assertEquals(5,users.size());
    // assertEquals("ion",users.get(0).surname);
  }

  @Test
  public void testHugeFile() {
    Iterable<User> users = new FileUserManager2().readUsersFromHugeFile("src/test/resources/users1.csv");
    User last = lastFrom(users);
    assertEquals("doc", last.firstName);
  }

  private User lastFrom(Iterable<User> users) {
    User last = null;
    for (User user : users) {
      last = user;
    }
    return last;
  }
}
