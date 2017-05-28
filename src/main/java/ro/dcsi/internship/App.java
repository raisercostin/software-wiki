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

public class App {
	private static String[] headersList ;
	private static List<String> users = new ArrayList<>();
	private static List<String> emails = new ArrayList<>();
	private static List<String[]> lineToAdd = new ArrayList<>();

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
	      String [] nextLine;
	      headersList = reader.readNext();
	      int lineNumber = 1;
	      while ((nextLine = reader.readNext()) != null) {
	        lineNumber++;
	        System.out.print("Line # " + lineNumber + ": ");

	        // nextLine[] is an array of values from the line
	       readLine(headersList,nextLine);
	     lineToAdd.add(nextLine);
	      System.out.println();
	      }
		
	}
	
	static void readLine(String[] headersList,String[] nextLine){
		for(int i =0; i < headersList.length; i++){
			System.out.print(nextLine[i] + " ,");
		}
	}

	static void writeCSV(File file) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(file), ',');
		writer.writeNext(headersList);

		for(int i=0 ; i<lineToAdd.size(); i++){
			writer.writeNext(lineToAdd.get(i));
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
