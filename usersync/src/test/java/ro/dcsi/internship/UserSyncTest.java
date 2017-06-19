package ro.dcsi.internship;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class UserSyncTest {

	@Test
	public void test() {

		List<User> users = UserSync.readUser("users1.csv");
		assertNotNull(users);
		assertEquals(5,users.size());
	}

}
