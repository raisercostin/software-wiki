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
		List<User> users = CsvExporter.readUsers(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).other);
		assertNotNull(users.get(0).username);
		assertEquals("asda,adfas,hkjhkjh,",User.other);
		assertEquals("alice",users.get(0).getUsername());
		assertEquals("alice@dcsi.ro",User.email);
		assertEquals(100,users.size());
		
	}
}
