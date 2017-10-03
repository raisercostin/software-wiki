package ro.dsci.internship;

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

	private void testWithSpecificUserSyncImplementation(UserDao dao) {
		List<User> users = dao.readUsers(locatie);
		Assert.assertEquals(4, users.size());
		Assert.assertEquals("firstuser@gmail.com", users.get(0).email);

		dao.writeUsers(users, locatie2);
    Path p1 = Paths.get(locatie2);
    boolean exists = Files.exists(p1);
    Assert.assertTrue("everything ok", exists);
  }
}
