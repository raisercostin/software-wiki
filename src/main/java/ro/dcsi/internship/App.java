package ro.dcsi.internship;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class App {
	private static List<String[]> users;
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
		CSVReader reader = new CSVReader(new FileReader(file), ',', '\'', 1);
		// TODO works with a 100G csv file?
		// while ((nextLine = reader.readNext()) != null && count < 101) {
		// count++;
		// System.out.println(nextLine[0]);
		// }
		users = reader.readAll();
		reader.close();
		for (String[] currentUser : users) {
			System.out.println(Arrays.toString(currentUser));
		}
	}

	static void writeCSV(File file) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(file));
		for (String[] currentUser : users) {
			writer.writeNext(Arrays.toString(currentUser).replaceAll("\\[|\\]", "").split(","));
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
