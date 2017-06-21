package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserSync {

	public static List<User> readUsers(String csvFile) {
		String cvsSplitBy = ",";
		List<User> users = new ArrayList<User>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] info = line.split(cvsSplitBy);
				users.add(new User(info[1]));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return users;
	}
}
