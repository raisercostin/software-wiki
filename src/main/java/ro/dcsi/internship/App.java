package ro.dcsi.internship;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class App {
	private static String header[];
	private static List<String[]> users = new ArrayList<String[]>();
	// TODO review this removal of final
	private static File importedUsersFile = new File("src/test/resources/importedUsers.csv");
	// TODO review this removal of final
	private static File exportedUsersFile = new File("target/exportedUsers.csv");

	public static void main(String... args) throws IOException {
		export("src/test/resources/importedUsers.csv", "target/exportedUsers.csv");
	}

	public static File getImportedusersfile() {
		return importedUsersFile;
	}

	public static File getExportedusersfile() {
		return exportedUsersFile;
	}

	static void readCSV(File file) throws IOException {
		String currentUser[];
		CSVReader reader = new CSVReader(new FileReader(file));
		header = reader.readNext();
		if (header == null)
			System.out.println("There are no entries! The CSV is empty!");
		else {
			while ((currentUser = reader.readNext()) != null) {
				users.add(currentUser);
				System.out.println(Arrays.toString(users.get(users.size() - 1)));
			}
		}
		reader.close();
	}

	static void writeCSV(File file) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(file), ',', '\u0000');
		writer.writeNext(header);
		for (String[] currentUser : users) {
			writer.writeNext(currentUser);
		}
		writer.close();
	}

	public static void export(String importFile, String exportFile) throws IOException {
		importedUsersFile = new File(importFile);
		exportedUsersFile = new File(exportFile);
		readCSV(importedUsersFile);
		writeCSV(exportedUsersFile);
	}
}
