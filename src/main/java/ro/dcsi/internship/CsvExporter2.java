package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvExporter2 {
	CsvExporter2() {
		System.out.println("csv exporter called");
	}

	public void export(String inputFileName, String outputFileName) throws IOException {
		FileReader fileReader = new FileReader(inputFileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		FileWriter fileWriter = new FileWriter(outputFileName);
		String s;
		bufferedReader.readLine();
		try {
			while ((s = bufferedReader.readLine()) != null) {
				fileWriter.append(s + " \n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileReader.close();
		fileWriter.close();
	}

	public String readHeading(String fileName) {
		String s = new String();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			s = br.readLine();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return s;
	}

	public ArrayList<User> readUsers(String fileName) {
		ArrayList<User> userArray = new ArrayList<User>();
		try (FileReader fileReader = new FileReader(fileName)) {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String s;
			String[] splited;
			bufferedReader.readLine();
			while ((s = bufferedReader.readLine()) != null) {
				splited = s.split(",");
				userArray.add(new User(splited[0], splited[1], splited[2]));
			}
			return userArray;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<User> readWithHeader(String fileName) {
		ArrayList<User> users = new ArrayList<User>();
		try (FileReader myFile = new FileReader(fileName)) {
			CSVFormat myFormat = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().withRecordSeparator('\n');
			CSVParser myParser = new CSVParser(myFile, myFormat);
			for (CSVRecord record : myParser) {
				users.add(new User(record.get("username"), record.get("Email"), record.get("Name")));
				//System.out.println(record.get("username") + " " + record.get("Email") + " " + record.get("Name"));
			}
			myParser.close();
			return users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}
