package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvExporter {
	CsvExporter() {
		System.out.println("instantiated");
	}

	public void export(String inputFilename, String outputFileName) throws IOException {
		FileReader fr = new FileReader(inputFilename);
		BufferedReader br = new BufferedReader(fr);
		FileWriter f0 = new FileWriter(outputFileName);
		String s;
		try {
			while ((s = br.readLine()) != null) {
				f0.append(s + ",");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		fr.close();
		f0.close();

	}

	public List<User> readUsers(String fileName) {
		ArrayList<User> list = new ArrayList<>();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				list.add(new User("user", "email", s));
			}
			return list;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
