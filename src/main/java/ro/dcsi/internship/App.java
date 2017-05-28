package ro.dcsi.internship;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
	private static List<String[]> users;
	// TODO review this removal of final
	private static File importedUsersFile = new File("src/test/resources/importedUsers.csv");
	// TODO review this removal of final
	private static File exportedUsersFile = new File("target/exportedUsers.csv");

	public static void main(String... args) throws IOException {
		export("src/test/resources/importedUsers.csv", "target/exportedUsers.csv");
	}

	public static void main2(String[] args) throws IOException {
		new CsvExporter().export("src/main/resources/users100.csv", "target/users100out.csv");
		List<User> users = new CsvExporter().readUsers("target/users100out.csv");
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
	public static void main3(String[] args) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			br = new BufferedReader(new FileReader("HundredUsers.csv"));
			sb = new StringBuilder();
			String line = br.readLine();
			String fileName = "HundredUsers.csv";
			File file = new File(fileName);
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String text = sb.toString();
			System.out.println(text);
			
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String data = scan.next();
				String[] values = data.split(",");
				for (String word : values) {
					System.out.println(word);
				}
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}

	}
}