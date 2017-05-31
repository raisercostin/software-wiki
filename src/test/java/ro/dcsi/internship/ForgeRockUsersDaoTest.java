package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ForgeRockUsersDaoTest extends CsvExporterTest {
	@Override
	UserDao exporter() {
		//return new ForgeRockUserDao("localhost:8080");
		return new ForgeRockUserDao("dcs-xps:8080");
	}

	@Test
	public void test() throws IOException {
		//We trigger the test from here to run it simpler from eclipse
		super.test();
	}
	@Test
	public void readUsersTest() throws IOException {
		List<User> users = exporter().load("no reading from file !!!!!!!!!!!");
		assertNotNull(users);
		assertEquals(0, users.size());
		String email = "raisercostin+testuser1@gmail.com";
		User user = new User("testuser1",email);
		assertEquals(email, user.email);
		exporter().save(Lists.newArrayList(user),"no writing to file !!!!!");
		List<User> users2 = exporter().load("no reading from file !!!!!!!!!!!");
		assertNotNull(users2);
		assertEquals(1, users2.size());
		assertEquals("testuser1", users.get(0).username);
		assertEquals(email, users.get(0).email);
	}	
}
