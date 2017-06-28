package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;

public class ForgeRockDBTest {
	@Test
	public void test() {
		/* TODO general tests */
		ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
		User user = db.getUser("75dbcfad-fb4d-4963-81fc-6a8c7175ffce");
		assertEquals(user.getAttributeValue("city"), "buch");
	}

	@Test
	public void userExistsTest() {
		/* TODO general tests */
		ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
		assertTrue(db.userExists("75dbcfad-fb4d-4963-81fc-6a8c7175ffce"));
		assertFalse(db.userExists("sir"));
	}
	
	@Test
	public void deleteUserTest() {
		/* TODO general tests */
		ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
		assertTrue(db.deleteUser("75dbcfad-fb4d-4963-81fc-6a8c7175ffce"));
		assertFalse(db.deleteUser("sir"));
	}
	
	@Test
	public void updateUserTest() {
		/* TODO general tests */
		Hashtable<String, String> attributes = new Hashtable<String, String>();
		attributes.put("_id", "Joe");
		attributes.put("mail", "joe@ex.com");
		User user = new User("Joe", attributes);
		ForgeRockDB db = new ForgeRockDB("http://localhost:8080", "openidm-admin", "openidm-admin");
		db.updateUser(user);
	}
}
