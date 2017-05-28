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
		String s;
		try (FileReader fr = new FileReader(inputFilename);
			FileWriter fo = new FileWriter(outputFileName);
			) {
			BufferedReader br = new BufferedReader(fr);
			while ((s = br.readLine()) != null) {
				fo.append(s + ",");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readHeading(String fileName) {
		ArrayList<User> users = new ArrayList<User>();
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
		List<User> users = new ArrayList<>();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			String s;
			String[] splited;
			while ((s = br.readLine()) != null) {
				splited = s.split(",");
				users.add(new User(splited[0], splited[1], splited[2]));
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
