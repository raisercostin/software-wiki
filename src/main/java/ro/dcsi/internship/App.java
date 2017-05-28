package ro.dcsi.internship;


import java.io.IOException;

public class App {
	public class CsvExporter {

	}

	public static void main(String[] args) throws IOException {
		new CsvExporter().export("users100.csv", "target/users100out.csv");
		new CsvExporter().readUser("target/users100out.csv");
	}
}
