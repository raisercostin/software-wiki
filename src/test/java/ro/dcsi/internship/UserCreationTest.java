package ro.dcsi.internship;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class UserCreationTest {
	@Test
	public void testImportedFileExists() throws IOException {
		App.main(null);
		assertTrue("Numarul de useri nu este pozitiv: "+App.getExportedusersfile().length(),App.getImportedusersfile().length() > 0);
		assertEquals(11998,App.getImportedusersfile().length());
	}
	@Test
	public void testExportedFile() throws IOException {
		App.main(null);
		assertTrue("Numarul de useri nu este pozitiv: "+App.getExportedusersfile().length(),App.getExportedusersfile().length() > 0);
		assertEquals(12991,App.getExportedusersfile().length());
		assertEquals(App.getImportedusersfile().length()
 				,App.getExportedusersfile().length());
	}
}
