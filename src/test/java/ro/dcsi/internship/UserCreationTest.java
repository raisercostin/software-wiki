package ro.dcsi.internship;

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
		App.export(importedUsersFile, "target/exportedUsers1.csv");
		assertEquals("exportedUsers1.csv", App.getExportedusersfile().getName());
		assertTrue("file exits", App.getExportedusersfile().exists());

		App.export(importedUsersFile, "target/exportedUsers2.csv");
		assertEquals("exportedUsers2.csv", App.getExportedusersfile().getName());
		assertTrue("file exits", App.getExportedusersfile().exists());
	}

	@Test
	public void testImportedFileExists() throws IOException {
		App.main(importedUsersFile, exportedUsersFile);
		App.main(importedUsersFile, "target/exportedUsers2.csv");
		assertTrue("Numarul de useri nu este pozitiv: " + App.getExportedusersfile().length(),
				App.getImportedusersfile().length() > 0);
		assertEquals(11998, App.getImportedusersfile().length());
	}

	@Test
	@Ignore
	public void testExportedFile() throws IOException {
		App.main(null);
		assertTrue("Numarul de useri nu este pozitiv: " + App.getExportedusersfile().length(),
				App.getExportedusersfile().length() > 0);
		assertEquals(12991, App.getExportedusersfile().length());
		assertEquals(App.getImportedusersfile().length(), App.getExportedusersfile().length());
	}
}
