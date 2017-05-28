package ro.dcsi.internship;

public class OpenCsvExporterTest extends CsvExporterTest {
	@Override
	UserDao exporter() {
		return new OpenCsvFileUserDao();
	}
}
