package ro.dcsi.internship;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class App {
	private static String line[];
	private static String header[];
	private static int entries = 0;
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
		CSVReader reader = new CSVReader(new FileReader(file));
		header = reader.readNext();
		if (header == null)
			System.out.println("There are no entries! The CSV is empty!");
		else {
			while ((line = reader.readNext()) != null) {
				entries++;
				System.out.print("Line # " + entries + ": ");
				for (int i = 0; i < line.length; i++)
					System.out.print(line[i] + " ");
				System.out.println();
			}
		}
		reader.close();
	}

	static void writeCSV(File file) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(file));
		// for (String[] currentUser : users) {
		// writer.writeNext(Arrays.toString(currentUser).replaceAll("\\[|\\]",
		// "").split(","));
		// }
		writer.close();
	}

	public static void export(String importFile, String exportFile) throws IOException {
		importedUsersFile = new File(importFile);
		exportedUsersFile = new File(exportFile);
		readCSV(importedUsersFile);
		// writeCSV(exportedUsersFile);
	}
}
