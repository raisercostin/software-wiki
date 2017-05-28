package ro.dcsi.internship;

import java.io.IOException;

public class App {
	public static void main(String... args) throws IOException {
		copyByImportAndExport("src/test/resources/users.csv", "target/exportedUsers.csv");
	}

	public static void copyByImportAndExport(String importFile, String exportFile) throws IOException {
		UserDao users = new OpenCsvFileUserDao();
		users.save(users.load(importFile), exportFile);
	}
}