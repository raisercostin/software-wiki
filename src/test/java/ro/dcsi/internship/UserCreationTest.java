package ro.dcsi.internship;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserCreationTest {

	@Test
	public void test() {
		if(App.getExportedusersfile().length()>0){
			assertTrue(true);
		}
		else if(App.getImportedusersfile().length()>0)
		{
			fail("File was not generated");
		}
	}

}
