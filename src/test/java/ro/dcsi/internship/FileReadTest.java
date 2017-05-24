package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;


public class FileReadTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test 
	public void readCSVSample() throws IOException {
		String csvFile1 = "/home/madalin/Workspace/Eclipse/WORK/UserSyncApp/ship1/src/test/resources/sample1.csv";
		String csvFile2 = "/home/madalin/Workspace/Eclipse/WORK/UserSyncApp/ship1/src/test/resources/sample2.csv";
		
		BufferedReader br1 = new BufferedReader(new FileReader(csvFile1));
		BufferedReader br2 = new BufferedReader(new FileReader(csvFile2));
	}
	
}
