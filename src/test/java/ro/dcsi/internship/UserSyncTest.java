package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UserSyncTest {

	@Test
	public void test() {
		List<User> users = UserSync.readUsers("src/test/resources/ceva.csv");
		assertEquals(5, users.size());
		assertEquals("Andrei", users.get(0).name);
	}
	@Test
	public void testHugeFile() {
		Iterable<User> users = UserSync.readUsersFromHugeFile("src/test/resources/ceva.csv"); // implementare iterator fara buffer (citire cate un entry o data)
		User last = lastFrom(users);
		
		assertEquals("Iulian",last.name);
		
	}
	private User lastFrom(Iterable<User> users) {
		User last = null;
		for (User user : users) {
			last = user;
		}
		return last;
	}
}
