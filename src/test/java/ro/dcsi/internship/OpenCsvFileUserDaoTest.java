package ro.dcsi.internship;

import java.io.IOException;

import org.junit.Test;

public class OpenCsvFileUserDaoTest extends CsvFileUserDaoTest {
	@Override
	UserDao exporter() {
		return new OpenCsvFileUserDao();
	}

	@Test
	public void test() throws IOException {
		//We trigger the test from here to run it simpler from eclipse
		super.test();
}
	@Test
	public void readUsersTest() throws IOException {
		super.readUsersTest();
	}
}
