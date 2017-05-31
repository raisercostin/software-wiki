package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.google.common.collect.Lists;

public class ForgeRockUserDaoTest extends CsvFileUserDaoTest {
	private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ForgeRockUserDaoTest.class);

	@Override
	ForgeRockUsersDao exporter() {
		return new ForgeRockUsersDao("dcs-xps:8080");
	}
	@Override
	@Test
	public void readUsersTest() throws IOException {
		List<User> users = exporter().load("doesn't matter the name");
	}
	@Test
	public void addOneUserTest() throws IOException {
		List<User> users = exporter().load("doesn't matter the name2");
		logger.info(users.toString());
		User user = new User("raisercostin","raisercostin+dcsi@gmail.com");
		exporter().deleteIfExists(user.username);
		exporter().save(Lists.newArrayList(user),"doesn't matter the name1");
		List<User> users2 = exporter().load("doesn't matter the name2");
		Assert.assertEquals(user.toString(),users2.get(0).toString());
	}
	@Test
	public void create10Users() throws IOException {
		int alreadyExistingUsers = exporter().load("doesn't matter the name2").size();
		List<User> users = generateUsers(1000,"user",10,"@gmail.com");
		exporter().save(users, "");
		List<User> actual = exporter().load("");
		assertEquals(alreadyExistingUsers+users.size(),actual.size());
	}
	@Test
	public void exportUsersFromFile() throws IOException {
		List<User> users = new OpenCsvFileUserDao().load("src/test/resources/sample3"
				+ ".csv");
		exporter().save(users, "nu conteaza");
	}
}

