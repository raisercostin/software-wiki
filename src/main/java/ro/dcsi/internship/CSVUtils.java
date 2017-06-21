package ro.dcsi.internship;

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

	private Scanner scanner;

	private static boolean enterDetected = false;
	private static List<String> res;

	public CSVUtils(String csvFile) {
		try {
			scanner = new Scanner(new File(csvFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param csvFile
	 *            a string that contains the adress to the csv file on the disk
	 * @return data read from the CSV file
	 * @throws FileNotFoundException
	 * @author Alin Penea
	 */
	public static List<List<String>> parseCSVFile(String csvFile) throws FileNotFoundException {
		// pastrarea datelor sub forma unei matrici bidimensionale
		List<List<String>> date = new ArrayList<List<String>>();
		Scanner scanner = new Scanner(new File(csvFile));
		List<String> line;
		// atata timp cat mai exista linii de citit din fisier
		while (scanner.hasNext()) {
			// citeste urmatoarea linie si parseaza datele
			line = parseLine(scanner.nextLine());
			// in cazul in care linia contine CRLF in interiorul ghilimelelor,
			// parseaza si alipeste de linia curenta urmatoarea linie
			while (enterDetected) {
				line = parseLine(scanner.nextLine());
			}
			// adauga la matricea bidimensionala linia curenta
			date.add(line);
		}
		scanner.close();
		return date;
	}

	public boolean scannerHasNext() {
		return scanner.hasNext();
	}

	public List<String> parseHugeCSVFile() {
		List<String> line = null;

		line = parseLine(scanner.nextLine());

		while (enterDetected) {
			line = parseLine(scanner.nextLine());
		}

		return line;
	}

	private static List<String> parseLine(String csvLine) {

		List<String> result = new ArrayList<>();
		char[] charCsvLine = csvLine.toCharArray();

		char separator = ',';
		char quote = '"';
		// daca caracterul curent este primul caracter din tokenul curent
		boolean firstChar = true;
		// daca caracterul curent se afla intre ghilimele
		boolean inQuotes = false;
		// daca tokenul curent este primul token dupa ce a fost prezent un enter
		// intre ghilimele
		boolean firstEntryAfterEnter = false;

		// daca linia anterioara a continul enter intre ghilimele, initializam
		// variabilele corespunzator
		if (enterDetected) {
			result = res;
			firstChar = false;
			inQuotes = true;
			firstEntryAfterEnter = true;
			enterDetected = false;
		}

		StringBuffer currentEntry = new StringBuffer();

		for (int i = 0; i < charCsvLine.length; i++) {
			// caracterul procesat curent
			char ch = charCsvLine[i];
			// caracterul curent este in interiorul unor ghilimele
			if (inQuotes) {
				// este prezent caracterul ghilimele in blocul curent
				if (ch == quote && i + 1 < charCsvLine.length && charCsvLine[i + 1] == '"') {
					currentEntry.append(ch);
					i++;
				} else if (ch == quote && (i + 1 == charCsvLine.length || charCsvLine[i + 1] == ',')) {
					// este prezent caracterul ghilimele, fara sa fie dublat,
					// inseamna ca se termina textul din interiorul ghilimelelor
					inQuotes = false;
				} else {
					currentEntry.append(ch);
					// daca s-a ajuns la ultimul caracter de pe linie si inca nu
					// s-au inchis ghilimelele inseamna ca exista un CRLF, deci
					// va trebui sa dam trigger la enterDetected
					if (i == charCsvLine.length - 1) {
						enterDetected = true;
						currentEntry.append('\n');
					}
				}
			} else {
				// cazul in care ghilimelele nu sunt prezente pentru token
				// daca primul caracter este ghilimele atunci inseamna ca
				// tokenul se afla plasat intre ghilimele
				if (ch == quote && firstChar) {
					inQuotes = true;
					firstChar = false;

				} else if (ch == separator) {
					// daca este caracter separator si nu este primul token dupa
					// un enter intre ghilimele, atunci il adaugam pur si simplu
					// la rezultatul liniei respective
					if (!firstEntryAfterEnter) {
						result.add(currentEntry.toString());
					} else {
						// daca este caracter separator si este primul token
						// dupa un enter intre ghilimele, atunci vom concatena
						// ultimul token cu tokenul curent (pentru ca sunt
						// acelasi token de fapt), si apoi il adaugam la
						// rezultatul liniei
						String aux = result.get(result.size() - 1);
						result.set(result.size() - 1, aux + currentEntry.toString());
						firstEntryAfterEnter = false;
					}
					currentEntry = new StringBuffer();
					inQuotes = false;
					firstChar = true;

				} else if (ch == '\n') {
					// daca caracterul este CRLF inseamna ca am terminat linia
					break;
				} else {
					// altfel este un caracter oarecare si il adaugam la tokenul
					// curent
					currentEntry.append(ch);
					firstChar = false;
				}
			}
		}

		// aceleasi operatii ca mai sus in cazul separatorului, dar caz special
		// pentru ultimul token de pe linie, intrucat RFC 4180 specifica faptul
		// ca ultimul token de pe linie nu se termina cu separator
		if (!firstEntryAfterEnter) {
			result.add(currentEntry.toString());
		} else {
			firstEntryAfterEnter = false;
			String aux = result.get(result.size() - 1);
			result.set(result.size() - 1, aux + currentEntry.toString());
		}
		// salvam linia in cazul in care aceasta ar contine un CRLF intre
		// ghilimele, si trebuie lipita cu urmatoarea
		res = result;
		return result;
	}

}