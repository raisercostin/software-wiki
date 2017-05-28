package ro.dcsi.internship;

import java.io.IOException;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		new CsvExporter2().export("src/test/resources/users100.csv", "target/users100out.csv");
		users = new CsvExporter2().readUsers("target/users100out.csv");
		new CsvExporter2().readWithHeader("src/test/resources/users100.csv");
	}
}
