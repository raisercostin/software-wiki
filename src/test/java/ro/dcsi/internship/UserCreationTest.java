package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.CSVReader;

public class UserCreationTest {

	@Test
	public void test() {
		
		String entry = "";
		List myEntries = null;
		App.writeFile(entry);
		   try {
			   CSVReader reader = new CSVReader(new FileReader("users.csv"));
			 myEntries = reader.readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   Assert.assertNotNull(myEntries);
		
	}

}
