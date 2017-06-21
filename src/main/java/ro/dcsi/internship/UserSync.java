package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserSync {

	public static List<User> readUsers(String csvFile) {
		List<User> users = new ArrayList<User>();
		List<List<String>> csvList;
		try {
			csvList = CSVUtils.parseCSVFile(csvFile);
			for (List<String> row : csvList) {
				users.add(new User(row.get(0)));
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fisierul csv nu a fost gasit la calea specificata");
			e.printStackTrace();
		}

		return users;
	}

	public static Iterable<User> readUsersFromHugeFile(String csvFile) {
		return new UserList(csvFile);
	}

}
