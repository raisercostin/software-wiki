package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSync {
	public static List<User> readUsers(String csvFile) {
		try(Scanner scanner = new Scanner(new File(csvFile));){
			List<User> all = new ArrayList<>();
			while (scanner.hasNext()) {
				List<String> line = CSVUtils.parseLine(scanner.nextLine());
				User user = new User(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4));
				all.add(user);
				System.out.println(user);
			}
			return all;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
