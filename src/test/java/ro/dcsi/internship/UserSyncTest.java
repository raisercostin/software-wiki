package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UserSyncTest {

	@Test
	public void test() {
		List<User> users = UserSync.readUsers("src/test/resources/SampleCSVFile.csv");// "users.csv");
		assertEquals(100, users.size());
		assertEquals("Eldon Base for stackable storage shelf", users.get(0).name);
	}
	
	@Test(expected=RuntimeException.class)
	public void testFileNotFound() {
		List<User> users = UserSync.readUsers("src/test/resources/SampleCSVFile-non-existing.csv");// "users.csv");
	}
	
	@Test
	public void writeText() throws IOException{

        String csvFile = "src/test/resources/SampleWriteFile.csv";
        FileWriter writer = new FileWriter(csvFile);

        UserSync.writeLine(writer, Arrays.asList("a", "b", "c", "d"));

        //custom separator + quote
        UserSync.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), ',', '"');

        //custom separator + quote
        UserSync.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"), '|', '\'');

        //double-quotes
        UserSync.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));


        writer.flush();
        writer.close();

	}
	
}
