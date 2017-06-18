package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author Alin Penea
 *
 */
public class CSVUtils {

	private static boolean enterDetected = false;
	private static List<String> res;

	public static void main(String[] args) {

		String csvFile = "ceva.csv";

		List<List<String>> date;
		try {
			date = parseCSVFile(csvFile);
			for (int i = 0; i < date.size(); i++) {
				System.out.print("[");
				for (int j = 0; j < date.get(i).size(); j++)
					System.out.print(date.get(i).get(j) + ";");
				System.out.println("]");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error reading from file");
			e.printStackTrace();
		}

	}

	public static List<List<String>> parseCSVFile(String csvFile) throws FileNotFoundException {
		List<List<String>> date = new ArrayList<List<String>>();
		Scanner scanner = new Scanner(new File(csvFile));
		List<String> line;
		while (scanner.hasNext()) {
			line = parseLine(scanner.nextLine());
			while (enterDetected) {
				line = parseLine(scanner.nextLine());
			}
			date.add(line);

		}
		scanner.close();
		return date;
	}

	private static List<String> parseLine(String csvLine) {

		List<String> result = new ArrayList<>();
		char[] charCsvLine = csvLine.toCharArray();

		char separator = ',';
		char quote = '"';

		boolean firstChar = true;
		boolean inQuotes = false;
		boolean firstEntryAfterEnter = false;

		if (enterDetected) {
			result = res;
			firstChar = false;
			inQuotes = true;
			firstEntryAfterEnter = true;
			enterDetected = false;
		}

		StringBuffer currentEntry = new StringBuffer();

		for (int i = 0; i < charCsvLine.length; i++) {
			char ch = charCsvLine[i];
			if (inQuotes) {
				if (ch == quote && i + 1 < charCsvLine.length && charCsvLine[i + 1] == '"') {
					currentEntry.append(ch);
					i++;
				} else if (ch == quote && (i + 1 == charCsvLine.length || charCsvLine[i + 1] == ',')) {
					inQuotes = false;
				} else {
					currentEntry.append(ch);
					if (i == charCsvLine.length - 1) {
						enterDetected = true;
						currentEntry.append('\n');
					}
				}
			} else {
				if (ch == quote && firstChar) {
					inQuotes = true;
					firstChar = false;

				} else if (ch == separator) {
					if (!firstEntryAfterEnter) {
						result.add(currentEntry.toString());
					} else {
						String aux = result.get(result.size() - 1);
						result.set(result.size() - 1, aux + currentEntry.toString());
						firstEntryAfterEnter = false;
					}
					currentEntry = new StringBuffer();
					inQuotes = false;
					firstChar = true;

				} else if (ch == '\n') {
					// the end, break!
					break;
				} else {
					currentEntry.append(ch);
					firstChar = false;
				}
			}
		}

		if (!firstEntryAfterEnter) {
			result.add(currentEntry.toString());
		} else {
			firstEntryAfterEnter = false;
			String aux = result.get(result.size() - 1);
			result.set(result.size() - 1, aux + currentEntry.toString());
		}
		res = result;
		return result;
	}

}