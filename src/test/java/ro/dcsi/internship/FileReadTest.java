package ro.dcsi.internship;

import static org.junit.Assert.fail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.junit.Ignore;
import org.junit.Test;
public class FileReadTest {
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
	public void readUsersTest() throws IOException {
		List<User> users = UserManager.readUsers("src/test/resources/sample1.csv");
		assertNotNull(users);
		assertEquals(3, users.size());
		assertEquals("Frank", users.get(0).username);
		assertEquals("Frank", users.get(0).email);
	}

	@Test
	public void doATest() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/HundredUsers.csv")));
		assertTrue(br.readLine() != null);
	}

	@Test
	public void doATest2() throws IOException {
		String destination = "HundredUsers.csv";
		String csvFile = "HundredUsers.csv2";
		File file1 = new File(destination);
		File file2 = new File(csvFile);
		//BufferedReader buffReader = new BufferedReader(new FileReader(destination));
		Scanner scanner = new Scanner(csvFile);
		while(scanner.hasNextLine())
			scanner.nextLine();
	}
}