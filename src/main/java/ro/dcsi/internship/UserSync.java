package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSync {

	public static List<User> readUser(String title) {
		try (Scanner sc = new Scanner(new File(title)); Scanner sc2 = sc.useDelimiter(",[\\n\\r]{0,2}");) {
			List<User> lu = new ArrayList<User>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String w[] = line.split("[,]");
				String name = w[0] + " " + w[1];
				Integer age = Integer.parseInt(w[2]);
				Double salary = Double.parseDouble(w[3]);
				String details = "";
				for (int i = 4; i < w.length; i++)
					details = w[i] + " ";
				lu.add(new User(name, age, salary, details));
			}
			return lu;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Iterable<User> readUsersFromHugeFile(String title) {
		// throw new RuntimeException("Not implemented yet!!!!");
		Scanner sc = null;
		Iterable<User> lu = new ArrayList<User>();

		return lu;
	}

}
