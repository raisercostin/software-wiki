package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
		List<User> users = exporter.readUsers(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).other);
		assertEquals(16,users.size());
		assertEquals("sad,adas,asda,asdasdas,;jlakjsd,asdsa,asdas",users.get(0).other);
		assertEquals("alice",users.get(0).username);
		assertEquals("alice@dcsi.ro",users.get(0).email);
	}
}
