package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class AppTest {
	String fileName = "src/test/resources/users100.csv";
	String outFileName = "target/users100out.csv";
	CsvExporter2 exporter = new CsvExporter2();

	@Test
	public void test() throws IOException {
		assertTrue(new File(fileName).exists());
		new File(outFileName).delete();
		exporter.export(fileName, outFileName);
		assertTrue(new File(outFileName).exists());
	}

	@Test
	public void testSameFile() throws IOException {
		String outFileName2 = "target/users100out.csv";
		exporter.export(fileName, outFileName);
		exporter.export(outFileName, outFileName2);
		assertTrue(new File(outFileName2).exists());
	}

	@Test
	public void testFileNotEmpty() throws IOException {
		List<User> users = new CsvExporter2().readUsers(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).other);
		assertNotNull(users.get(0).username);
		assertEquals("Victor", users.get(0).getUsername());
		assertEquals("victor.ciresica@gmail.com", users.get(0).email);
		assertEquals(6, users.size());
	}

	@Test
	public void testApp() {
		CsvExporter2 export = new CsvExporter2();
		List<User> users = export.readUsers("src/test/resources/users.csv");
		assertEquals("username,email,other", export.readHeading("src/test/resources/users.csv"));
		assertEquals(8, users.size());
		for (User user : users.subList(1, users.size() - 1)) {
			Integer indexOfAt = user.email.indexOf("@");
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
		CsvExporter2 export = new CsvExporter2();
		List<User> users = export.readUsers("src/test/resources/users.csv-------NO-FILE-------");
	}
	@Test
	public void testCSVwithHeader() {
		CsvExporter2 exporter = new CsvExporter2();
		List<User> users = exporter.readWithHeader(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).email);
		assertNotNull(users.get(5).username);
		assertEquals(users.size(), exporter.readUsers(fileName).size());
		assertEquals(users.get(2).email, exporter.readUsers(fileName).get(2).email);
		assertEquals(users.get(3).email.contains("@"),true);
	}
}
