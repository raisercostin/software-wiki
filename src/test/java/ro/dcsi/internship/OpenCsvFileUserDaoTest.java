package ro.dcsi.internship;

public class OpenCsvFileUserDaoTest extends CsvFileUserDaoTest {
	@Override
	UserDao exporter() {
		return new OpenCsvFileUserDao();
	}
}
