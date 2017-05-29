package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvExporter {
	CsvExporter() {
		System.out.println("csv exporter called");
	}

	public void export(String inputFileName, String outputFileName) throws IOException {
		FileReader fileReader = new FileReader(inputFileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		FileWriter fileWriter = new FileWriter(outputFileName);
		String s;
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
		ArrayList<User> usersArray = new ArrayList<User>();
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

	public List<User> readUsers(String fileName) {
		ArrayList<User> userArray = new ArrayList<User>();
		try (FileReader fileReader = new FileReader(fileName)) {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String s;
			String[] splited;
			while ((s = bufferedReader.readLine()) != null) {
				splited = s.split(",");
				userArray.add(new User(splited[0], splited[1], splited[2]));
			}
			return userArray;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
