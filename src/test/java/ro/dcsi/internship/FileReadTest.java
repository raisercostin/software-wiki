package ro.dcsi.internship;

import static org.junit.Assert.*;
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
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;
public class FileReadTest {

	@Test
	@Ignore
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void doATest() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("c:\\Users\\Andrei\\maven workspace\\ship1\\src\\main\\resources\\HundredUsers.csv")));
		assertTrue(br.readLine() != null);
		

	}

	@Test
	public void doATest() throws IOException {
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
}