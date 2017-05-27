package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserCreationTest {
	@Test
	public void successfulCreation() throws IOException {
		App.export(App.getImportedusersfile().toString(),App.getExportedusersfile().toString());
		LineNumberReader exportedEntries = new LineNumberReader(new FileReader(App.getExportedusersfile()));
		LineNumberReader importedEntries = new LineNumberReader(new FileReader(App.getImportedusersfile()));
		exportedEntries.skip(Long.MAX_VALUE);
		importedEntries.skip(Long.MAX_VALUE);
		Assert.assertEquals(exportedEntries.getLineNumber(), importedEntries.getLineNumber());
		exportedEntries.close();
		importedEntries.close();
	}
	
	@Test
	public void testImportedFileExists() throws IOException {
		assertTrue("Numarul de useri nu este pozitiv: " + App.getExportedusersfile().length(),
				App.getImportedusersfile().length() > 0);
		assertTrue(App.getImportedusersfile().exists());
	}

	@Test
	public void testExportedFile() throws IOException {
		App.export(App.getImportedusersfile().toString(), "target/exportedUsers1.csv");
		assertEquals("exportedUsers1.csv", App.getExportedusersfile().getName());
		
		App.export(App.getImportedusersfile().toString(), "target/exportedUsers2.csv");
		assertEquals("exportedUsers2.csv", App.getExportedusersfile().getName());
		
		assertTrue(App.getExportedusersfile().exists());
	}
}
