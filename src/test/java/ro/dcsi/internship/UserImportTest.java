package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.CSVReader;

public class UserImportTest {

	@Test
	public void fileExists() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("src/test/resources/users.csv"));
		List myEntries = reader.readAll();
		Assert.assertNotNull(myEntries);
		Assert.assertEquals(3,myEntries.size());
	}
}
