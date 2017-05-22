package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

public class FileReadTest {

	@Test
	@Ignore
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void readCSVSample() throws IOException {
		String csvFile1 = "src/test/resources/sample1.csv";
		String csvFile2 = "src/test/resources/sample2.csv";
		BufferedReader br1 = new BufferedReader(new FileReader(csvFile1));
		BufferedReader br2 = new BufferedReader(new FileReader(csvFile2));
		assertEquals(
				"FIRST NAME ,LAST NAME,USERNAME ,PASSWORD ,EMAIL ADDRESS,PHONE NUMBER,PASSPORT,GROUPS,USERCODE,TITLE,ADDRESS 1 ,ADDRESS 2,CITY,STATE,ZIP",
				br1.readLine());
	}

	@Test
	public void readUsersAlwaysNotNullTest() throws IOException {
		List<User> users = App.readUsers("src/test/resources/sample1.csv");
		assertNotNull(users);
	}

	@Test
	public void readUsersTest() throws IOException {
		List<User> users = App.readUsers("src/test/resources/sample1.csv");
		Assume.assumeNotNull(users);
		assertEquals(5, users.size());
	}

}
