package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class AppTest {
	String fileName = "src/test/resources/users100.csv";
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
		List<User> users = new CsvExporter().readUsers(fileName);
		assertNotNull(users);
		assertNotNull(users.get(0).other);
		assertNotNull(users.get(0).username);
		//assertEquals("asda,adfas,hkjhkjh,",User.other);
		System.out.println(users.get(0).getUsername());
		assertEquals("Victor",users.get(0).getUsername());
		assertEquals("victor.ciresica@gmail.com",users.get(0).email);
		assertEquals(6,users.size());
	}
	@Test
	public void testApp()
    {
        CsvExporter2 export = new CsvExporter2();
        List<User> users = export.readUsers("src/main/resources/users.csv");
        assertEquals("username,email,other",export.readHeading("src/main/resources/users.csv"));
        assertEquals(9,users.size());
        for(User user:users.subList(1, users.size()-1)){
            Integer indexOfAt = user.email.indexOf("@");
            System.out.println(user.email);
            System.out.println(indexOfAt);
            System.out.println(user.email.length());
            String at = user.email.substring(indexOfAt, user.email.length());
            System.out.println(at);
            assertEquals("@gmail.com",at);
        }
    }
}
