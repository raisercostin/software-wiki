package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class UserSyncTest {

	@Test
	public void test() {
		List<User> users = UserSync.readUsers("users1.csv");
		assertNotNull(users);
//		assertEquals(5,users.size());
//		assertEquals("ion",users.get(0).surname);
	}
	@Test
	public void testHugeFile() {
		Iterable<User> users = UserSync.readUsersFromHugeFile("users1.csv");
		User last = lastFrom(users);
		assertEquals("john",last.surname);
	}
	private User lastFrom(Iterable<User> users) {
		User last = null;
		for (User user : users) {
			last = user;
		}
		return last;
	}
}
