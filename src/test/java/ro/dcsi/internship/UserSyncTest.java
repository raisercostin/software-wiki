package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UserSyncTest {
	@Test
	public void test() {
		List<User> users = UserSync.readUsers("ceva.csv");
		assertEquals(5, users.size());
		assertEquals("Andrei", users.get(0).name);
		users.get(0).rights.add("salut");
		users.get(0).name = "b";
		users.get(0).rights = new ArrayList<>();
	}
}
