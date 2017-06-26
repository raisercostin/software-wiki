package ro.dcsi.internship;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserManagerTest {

	@Test
	public void emptyManagerTest() {
		UserManager um = new UserManager();
		assertEquals(um.numberOfUsers(), 0);
	}
	
	@Test
	public void addTest() {
		UserManager um = new UserManager();
		um.addUser(new User("john"));
		assertEquals(um.numberOfUsers(), 1);
		User user = um.getUser("john");
		assertEquals(user.username, "john");
	}
	
	@Test
	public void importTest() {
		UserManager um = new UserManager();
		um.importUsers("src/test/resources/users1.csv");
		User user = um.getUser("dj");
		assertEquals(user.getFirstName(), "John");
		assertEquals(user.hasPermission("w"), true);
		assertEquals(user.hasPermission("somethingNotThere"), false);
		assertEquals(user.hasPermission("r"), true);
		
		user = um.getUser("trail");
		assertEquals(user.getFirstName(), "doc");
		assertEquals(user.hasPermission("w"), true);
		assertEquals(user.hasPermission("somethingNotThere"), false);
		assertEquals(user.hasPermission("c"), true);
		
		System.out.println(um);
	}
}
