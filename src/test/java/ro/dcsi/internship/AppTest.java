package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class AppTest {
	@Test
	public void test() throws IOException{
		String fileName = "users100.csv";
		String outFileName = "users100out.csv";
		assertTrue(new File(fileName).exists());
		new File(outFileName).delete();
		App.export(fileName,outFileName);
		assertTrue(new File(outFileName).exists());
	}
	@Test
	public void testSameFile() throws IOException{
		String fileName = "users100.csv";
		String outFileName = "users100out.csv";
		String outFileName2 = "users100out-2.csv";
		App.export(fileName,outFileName);
		App.export(outFileName,outFileName2);
		assertTrue(new File(outFileName2).exists());
	}
}
