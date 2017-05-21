package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class App {

	public static void main(String[] args) {
		CSVWriter writer;
		String entry = "";
		int index;
		int n;
		int count = -1;
		CSVReader reader = null;

		readFile(reader, count);
		writeFile(entry);

	}

	public static void readFile(CSVReader reader, int count) {
		try {
			reader = new CSVReader(new FileReader("users.csv"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null && count < 101) {
				count++;
				System.out.println(nextLine[0]);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void writeFile(String entry) {
		CSVWriter writer;
		int index;
		int n;
		System.out.println("Insert numbers of users you want to export to file, even 1 000 000: ");
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		try {
			writer = new CSVWriter(new FileWriter("users.csv"), '\n');
			for (index = 0; index < n; index++) {
				entry += "user" + index + ",";
			}
			String[] entries = entry.split(",");
			writer.writeNext(entries);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
