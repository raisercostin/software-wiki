package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UserSyncTest {

	@Test
	public void test() {
		List<User> users = UserSync.readUsers("ceva.csv");
		assertEquals(5, users.size());
		assertEquals("", users.get(0).name);
	}

}
