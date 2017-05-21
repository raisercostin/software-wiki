package ro.dcsi.internship;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReadFileTest {
	App app = new App();
	
	@Test
	public void importFile() {
		app.importFile();
	}
	
	@Test
	public void exportFile() {
		app.exportFile();
	}

}
