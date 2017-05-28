package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvExporter {
	CsvExporter() {
		System.out.println("instantiated");
	}

	public void export(String inputFilename, String outputFileName) throws IOException {
		String s;
		try (FileReader fr = new FileReader(inputFilename); FileWriter fo = new FileWriter(outputFileName);) {
			BufferedReader br = new BufferedReader(fr);
			while ((s = br.readLine()) != null) {
				fo.append(s + ",");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<User> readUsers(String fileName) {
		ArrayList<User> list = new ArrayList<>();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			String s;
			String[] splited;
			while ((s = br.readLine()) != null) {
				splited = s.split(",");
				list.add(new User(splited[0], splited[1], splited[2]));
			}
			return list;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
