package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.google.common.collect.Lists;

public class ForgeRockUserDaoTest extends CsvFileUserDaoTest {
	@Override
	ForgeRockUsersDao exporter() {
		return new ForgeRockUsersDao();
	}
	@Override
	@Test
	public void readUsersTest() throws IOException {
		List<User> users = exporter().load("doesn't matter the name");
	}
	@Test
	public void addOneUserTest() throws IOException {
		List<User> users = exporter().load("doesn't matter the name2");
		System.out.println(users);
		User user = new User("raisercostin","raisercostin+dcsi@gmail.com");
		exporter().deleteIfExists(user.username);
		exporter().save(Lists.newArrayList(user),"doesn't matter the name1");
		List<User> users2 = exporter().load("doesn't matter the name2");
		Assert.assertEquals(user.toString(),users2.get(0).toString());
	}
	@Test
	public void create10Users() throws IOException {
		List<User> users = generateUsers("user",10,"@gmail.com");
		exporter().save(users, "");
		List<User> actual = exporter().load("");
		assertEquals(users.size(),actual.size());
	}
}

