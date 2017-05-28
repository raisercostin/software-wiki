package ro.dcsi.internship;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	}

	@Test
	@Ignore
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void doATest() throws IOException {
		String csvFile = "HundredUsers.csv2";
		Scanner scanner = new Scanner(csvFile);
		while(scanner.hasNextLine())
			scanner.nextLine();
		scanner.close();
	}
}
