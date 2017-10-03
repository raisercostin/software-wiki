package ro.dsci.internship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestUserSync {
	String locatie = "src/test/resources/CVSTest.csv";
	String locatie2 = "target/CVSTest2.csv";


  @Test
  public void testGabiUserDao() {
    GabrielUserDao userSync = new GabrielUserDao();
    testWithSpecificUserSyncImplementation(userSync);
  }

  @Test
  public void testGabiForgerockUserDao() {
    UserDao userSync = new GabrielForgerockUserDao();
    testWithSpecificUserSyncImplementation(userSync);
  }

	@Test
	public void testVladUserDao() {
    testWithSpecificUserSyncImplementation(new VladUserDao());
	}
	
	@Test public void testAbs(){
    System.out.println(Integer.MIN_VALUE);
    System.out.println(Math.abs(Integer.MIN_VALUE));
    System.out.println(Integer.MAX_VALUE);
    System.out.println(Math.abs(Integer.MAX_VALUE));
    System.out.println(System.currentTimeMillis());
	}

  @Test
  public void testIoanaUserDao() {
    testWithSpecificUserSyncImplementation(new IoanaUserDao());
  }

	private void testWithSpecificUserSyncImplementation(UserDao dao) throws RuntimeException{
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
}
