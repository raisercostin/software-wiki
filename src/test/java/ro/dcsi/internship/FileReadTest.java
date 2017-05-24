package ro.dcsi.internship;

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
}