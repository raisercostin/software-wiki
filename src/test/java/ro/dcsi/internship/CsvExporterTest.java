package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class CsvExporterTest {
	String fileName = "src/test/resources/users100.csv";
	String outFileName = "target/users100out.csv";
	CsvExporter exporter = new CsvExporter();

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
		List<User> users = new CsvExporter().readUsers(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).other);
		assertNotNull(users.get(0).username);
		// assertEquals("asda,adfas,hkjhkjh,",User.other);
		System.out.println(users.get(0).username);
		assertEquals("Victor", users.get(0).username);
		assertEquals("victor.ciresica@gmail.com", users.get(0).email);
		assertEquals(6, users.size());
	}

	@Test
	public void testApp() {
		CsvExporter export = new CsvExporter();
		List<User> users = export.readUsers("src/test/resources/users.csv");
		assertEquals("username,email,other", export.readHeading("src/test/resources/users.csv"));
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
		CsvExporter export = new CsvExporter();
		List<User> users = export.readUsers("src/test/resources/users.csv-------NO-FILE-------");
	}

	@Test
	public void readCSVSample() throws IOException {
		String csvFile1 = "src/test/resources/sample1.csv";
		String csvFile2 = "src/test/resources/sample2.csv";
		BufferedReader br1 = new BufferedReader(new FileReader(csvFile1));
		BufferedReader br2 = new BufferedReader(new FileReader(csvFile2));
		assertEquals(
				"FIRST NAME ,LAST NAME,USERNAME ,PASSWORD ,EMAIL,PHONE NUMBER,PASSPORT,GROUPS,USERCODE,TITLE,ADDRESS 1 ,ADDRESS 2,CITY,STATE,ZIP",
				br1.readLine());
	}

	@Test
	public void readUsersTest() throws IOException {
		List<User> users = new CsvExporter().readUsers("src/test/resources/sample1.csv");
		assertNotNull(users);
		assertEquals(3, users.size());
		assertEquals("friley", users.get(0).username);
		assertEquals("friley@kanab.org", users.get(0).email);
	}

	@Test
	public void doATest2() throws IOException {
		String destination = "HundredUsers.csv";
		String csvFile = "HundredUsers.csv2";
		File file1 = new File(destination);
		File file2 = new File(csvFile);
		// BufferedReader buffReader = new BufferedReader(new FileReader(destination));
		Scanner scanner = new Scanner(csvFile);
		while (scanner.hasNextLine())
			scanner.nextLine();
	}
}
