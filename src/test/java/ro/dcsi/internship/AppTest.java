package dcsi;

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
		String outFileName2 = "target/users100out.csv";
		exporter.export(fileName,outFileName);
		exporter.export(outFileName,outFileName2);
		assertTrue(new File(outFileName2).exists());
	}
	@Test
	public void testFileNotEmpty() throws IOException{
		List<String[]> users = new CsvExporter().readUsers(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).length);
		//assertEquals("asda,adfas,hkjhkjh,",User.other);
		System.out.println(users.get(0).splited.get(0));
		assertEquals("Victor",users.get(1).get(0));
		assertEquals("victor.ciresica@gmail.com",users.get(0).get(1));
		assertEquals(6,users.size());
		
	}
}
