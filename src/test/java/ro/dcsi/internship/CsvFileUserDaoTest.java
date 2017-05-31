package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;

public class CsvFileUserDaoTest {
	private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger("myspeciallog");

	String fileName = "src/test/resources/users100.csv";
	String outFileName = "target/users100out.csv";

	UserDao exporter() {
		return new CsvFileUserDao();
	}

	@Test
	public void testFileNotEmpty() throws IOException {
		List<User> users = exporter().load(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).firstname);
		assertNotNull(users.get(0).lastname);
		assertNotNull(users.get(0).username);
		// assertEquals("asda,adfas,hkjhkjh,",User.other);
		logger.info("aha -- "+users.get(0).username);
		assertEquals("Victor", users.get(0).username);
		assertEquals("victor.ciresica@gmail.com", users.get(0).email);
		assertEquals(6, users.size());
	}

	@Test
	public void testApp() {
		List<User> users = exporter().load("src/test/resources/users.csv");
		assertEquals("username,email,firstname,lastname", exporter().loadHeader("src/test/resources/users.csv"));
		assertEquals(8, users.size());
		for (User user : users) {
			Integer indexOfAt = user.email.indexOf("@");
			assertTrue("User email [" + user.email + "] should contain [@]", indexOfAt >= 0);
			System.out.println(user.email);
			System.out.println(indexOfAt);
			System.out.println(user.email.length());
			String at = user.email.substring(indexOfAt, user.email.length());
			System.out.println(at);
			assertEquals("@gmail.com", at);
		}
	}

	@Test(expected = RuntimeException.class)
	public void testExportWithMissingFile() {
		List<User> users = exporter().load("src/test/resources/users.csv-------NO-FILE-------");
	}

	@Test
	public void readCSVSample() throws IOException {
		String csvFile1 = "src/test/resources/sample1.csv";
		assertEquals(
				"FIRSTNAME ,LASTNAME,USERNAME ,PASSWORD ,EMAIL,PHONE NUMBER,PASSPORT,GROUPS,USERCODE,TITLE,ADDRESS 1 ,ADDRESS 2,CITY,STATE,ZIP",
				exporter().loadHeader(csvFile1));
	}

	@Test
	public void readUsersTest() throws IOException {
		List<User> users = exporter().load("src/test/resources/sample1.csv");
		assertNotNull(users);
		assertEquals(3, users.size());
		assertEquals("friley", users.get(0).username);
		assertEquals("friley@kanab.org", users.get(0).email);
	}

	@Test
	public void readUsersTestSpecialPass() throws IOException {
		List<User> users = exporter().load("src/test/resources/sample-special-pass.csv");
		assertNotNull(users);
		assertEquals(3, users.size());
		assertEquals("friley", users.get(0).username);
		assertEquals("friley@kanab.org", users.get(0).email);
	}

	@Test
	public void test() throws IOException {
		assertTrue(new File(fileName).exists());
		new File(outFileName).delete();
		String fileName = "src/test/resources/users.csv";
		String outFileName = "target/output-UsersStorageTest.csv";
		copy(fileName, outFileName);
		assertTrue(new File(outFileName).exists());
	}

	private void copy(String fileName, String outFileName) {
		List<User> users = exporter().load(fileName);
		exporter().save(users, outFileName);
	}

	@Test
	public void testSameFile() throws IOException {
		String outFileName2 = "target/users100out.csv";
		copy(fileName, outFileName);
		copy(outFileName, outFileName2);
		assertTrue(new File(outFileName2).exists());
	}

	@Test
	@Ignore
	public void testGenerate100Users() throws IOException {
		List<User> users = generateUsers(100);
		String file = "target/users-100generated-" + getClass().getSimpleName() + ".csv";
		exporter().save(users, file);
		List<User> loaded = exporter().load(file);
		assertEquals(100, loaded.size());
		assertEquals("User0", loaded.get(0).username);
		assertEquals("User99", loaded.get(99).username);
	}

	@Test
	public void testQuotesAreSaved() throws IOException {
		String specialName = "M c\"Donald,Ronald";
		User user = new User(specialName, "email@pebune.ro");
		String file = "target/specialUser-" + getClass().getSimpleName() + ".csv";
		exporter().save(Lists.newArrayList(user), file);
		List<User> actual = exporter().load(file);
		assertEquals(specialName, actual.get(0).username);
	}

	@Test(expected=RuntimeException.class)
	public void testEndOfLineAreNotSaved() throws IOException {
		new User("Mc\"Donald,Ron\nald", "email");
	}

	@Test(timeout = 10000)
	@Ignore
	public void testHugeNumberOfUsers() throws IOException {
		List<User> users = generateUsers(3000000);
		String file = "target/manyUsers-" + getClass().getSimpleName() + ".csv";
		exporter().save(users, file);
		List<User> actual = exporter().load(file);
		assertEquals(users.size(), actual.size());
	}

	public List<User> generateUsers(long nrOfUsersToGenerate) {
		return generateUsers(0,"User", nrOfUsersToGenerate, "@gmail.com");
	}

	public List<User> generateUsers(int startingId, String prefix, long nrOfUsersToGenerate, String emailServer) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < nrOfUsersToGenerate; i++) {
			users.add(new User(prefix + (i+startingId), prefix + (i+startingId) + emailServer));
		}
		return users;
	}
}
