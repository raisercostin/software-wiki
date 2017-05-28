package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.List;

public class UserStorage {
	private final CsvExporter exporter = new CsvExporter();
	private final String inputFilePath;
	private final String outputFilePath;
	private List<User> users = new ArrayList<User>();

	public UserStorage(String inputFilePath, String outputFilePath) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}

	public void exportUsers() {
		exporter.export(users, outputFilePath);
	}

	public void importUsers() {
		users = exporter.readUsers(inputFilePath);
	}

	public List<User> getUsers() {
		return users;
	}

	public void generateUsers(String filePath, int nrOfUsersToGenerate) {
		List<User> users = new ArrayList<>();
		for (int i = 1; i <= nrOfUsersToGenerate; i++) {
			users.add(new User("User" + i));
		}
		exporter.export(users, filePath);
	}
}
