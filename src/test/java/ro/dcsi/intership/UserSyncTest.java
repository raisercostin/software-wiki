package ro.dcsi.intership;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UserSyncTest {
	@Test
	public void test() {
		List<User> users = UserSync.readUsers("users.csv");
		assertEquals(5, users.size());
		assertEquals("ion", users.get(0).name);
	}
}
