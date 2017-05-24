package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.CSVReader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class UserCreationTest {
	private final static String importedUsersFile = "src/test/resources/importedUsers.csv";
	private final static String exportedUsersFile = "target/exportedUsers.csv";

	@Test
	public void testExportedFileExists() throws IOException {
		App2.export(importedUsersFile, "target/exportedUsers1.csv");
		assertEquals("exportedUsers1.csv", App2.getExportedusersfile().getName());
		assertTrue("file exits", App2.getExportedusersfile().exists());

		App2.export(importedUsersFile, "target/exportedUsers2.csv");
		assertEquals("exportedUsers2.csv", App2.getExportedusersfile().getName());
		assertTrue("file exits", App2.getExportedusersfile().exists());
	}

	@Test
	public void testImportedFileExists() throws IOException {
		App2.main(importedUsersFile, exportedUsersFile);
		App2.main(importedUsersFile, "target/exportedUsers2.csv");
		assertTrue("Numarul de useri nu este pozitiv: " + App2.getExportedusersfile().length(),
				App2.getImportedusersfile().length() > 0);
		assertEquals(11998, App2.getImportedusersfile().length());
	}

	@Test
	@Ignore
	public void testExportedFile() throws IOException {
		App2.main(null);
		assertTrue("Numarul de useri nu este pozitiv: " + App2.getExportedusersfile().length(),
				App2.getExportedusersfile().length() > 0);
		assertEquals(12991, App2.getExportedusersfile().length());
		assertEquals(App2.getImportedusersfile().length(), App2.getExportedusersfile().length());
	}

	@Test
	public void fileExists() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("src/test/resources/users.csv"));
		List myEntries = reader.readAll();
		Assert.assertNotNull(myEntries);
		Assert.assertEquals(3,myEntries.size());
	}
}
