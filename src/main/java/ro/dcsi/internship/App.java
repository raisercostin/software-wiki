package ro.dcsi.internship;

import java.io.IOException;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		new CsvExporter().export("users100.csv", "target/users100out.csv");
		users = new CsvExporter().readUsers("target/users100out.csv");
	}
}
