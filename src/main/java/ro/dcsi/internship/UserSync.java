package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserSync {

	public static List<User> readUsers(String csvFile) {
		try {
			List<User> users = new ArrayList<User>();
			List<List<String>> csvList = CSVUtils.parseCSVFile(csvFile);
			for (List<String> row : csvList) {
				users.add(new User(row.get(0)));
			}
			return users;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Fisierul csv nu a fost gasit la calea specificata", e);
		}
	}

	public static Iterable<User> readUsersFromHugeFile(String csvFile) {
		return new UserList(csvFile);
	}
}
