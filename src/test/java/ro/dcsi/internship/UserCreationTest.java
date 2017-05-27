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
	public void testImportedFileExists() throws IOException {
		assertTrue(App.getImportedusersfile().exists());
	}

	@Test
	public void testExportedFile() throws IOException {
		assertTrue(App.getExportedusersfile().exists());
	}

	@Test
	public void successfulCreation() throws IOException {
		LineNumberReader exportedEntries = new LineNumberReader(new FileReader(App.getExportedusersfile()));
		LineNumberReader importedEntries = new LineNumberReader(new FileReader(App.getImportedusersfile()));
		exportedEntries.skip(Long.MAX_VALUE);
		importedEntries.skip(Long.MAX_VALUE);
		Assert.assertEquals(exportedEntries.getLineNumber(), importedEntries.getLineNumber());
		exportedEntries.close();
		importedEntries.close();
	}
}
