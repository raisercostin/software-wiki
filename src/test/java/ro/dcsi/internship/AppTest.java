package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class AppTest {
	String fileName = "users100.csv";
	String outFileName = "target/users100out.csv";
	CsvExporter exporter = new CsvExporter();

	@Test
	public void test() throws IOException{
		assertTrue(new File(fileName).exists());
		new File(outFileName).delete();
		exporter.export(fileName,outFileName);
		assertTrue(new File(outFileName).exists());
	}
	@Test
	public void testSameFile() throws IOException{
		String outFileName2 = "target/users100out-2.csv";
		exporter.export(fileName,outFileName);
		exporter.export(outFileName,outFileName2);
		assertTrue(new File(outFileName2).exists());
	}
	@Test
	public void testFileNotEmpty() throws IOException{
		assertNotNull(exporter.readUsers(fileName));
		assertNotNull(exporter.readUsers(fileName).get(0).name);
		assertEquals(100,exporter.readUsers(fileName).size());
	}
}
