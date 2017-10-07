package ro.dcsi.internship;

import java.io.IOException;
import java.util.List;

public class App {
	public static void main(String... args) throws IOException {
		copyByImportAndExport("src/test/resources/users.csv", "target/exportedUsers.csv");
	}

	public static void copyByImportAndExport(String importFile, String exportFile) throws IOException {
		List<User> users = new OpenCsvFileUserDao().load(importFile);
		new OpenCsvFileUserDao().save(users,exportFile);
	}
}