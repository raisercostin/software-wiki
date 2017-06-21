package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UserSyncTest {

	@Test
	public void test() {
		List<User> users = UserSync.readUsers("src/test/resources/SampleCSVFile.csv");//"users.csv");
		assertEquals(100,users.size());
		assertEquals("Eldon Base for stackable storage shelf",users.get(0).name);
	}

}
