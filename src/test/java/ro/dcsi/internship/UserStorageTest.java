package ro.dcsi.internship;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserStorageTest {
	UserStorage storage = new UserStorage("input.csv","output.csv");
	
	@Test
	public void importFile() {
		storage.importUsers();
	}
	
	@Test
	public void exportFile() {
		storage.exportUsers();
	}

}
