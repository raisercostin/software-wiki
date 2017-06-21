package ro.dcsi.internship;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UserSyncTest {
	@Test
	public void test() {
		List<User> users = UserSync.readUser("src/test/resources/users1.csv");
		assertNotNull(users);
		assertEquals(3, users.size());
		assertEquals("ion andrei", users.get(0).name);
	}

	@Test
	public void testFileDoesntExist() {
		try {
			List<User> users = UserSync.readUser("src/test/resources/users1-nonexisting.csv");
			fail("should not return normally from readUser");
		} catch (RuntimeException e) {
			assertNotNull(e);
			assertTrue(e.getMessage().contains("nonexisting.csv"));
		}
	}

	@Test(expected = RuntimeException.class)
	public void testFileDoesntExistSimpleCheck() {
		UserSync.readUser("src/test/resources/users1-nonexisting.csv");
	}

	@Test
	public void testHugeFile() {
		Iterable<User> users = UserSync.readUsersFromHugeFile("users1.csv");
		User last = lastFrom(users);
		assertEquals("john", last.name);
	}

	private User lastFrom(Iterable<User> users) {
		User last = null;
		for (User user : users) {
			last = user;
		}
		return last;
	}

}
