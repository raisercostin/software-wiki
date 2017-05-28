package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserStorage {
	private final CsvExporter exporter = new CsvExporter();

	private final String inputFilePath;
	private final String outputFilePath;
	private File file = null;
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedWriter out = null;
	private BufferedReader in = null;

	private List<User> users = new ArrayList<User>();

	public UserStorage(String inputFilePath, String outputFilePath) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}

	public void exportUsers() {
		exporter.export(users, outputFilePath);
		try {
			file = new File(outputFilePath);
			fw = new FileWriter(file);
			out = new BufferedWriter(fw);

			Iterator<User> iterator = users.iterator();

			User tmp;

			while (iterator.hasNext()) {
				tmp = (User) iterator.next();
				out.write(tmp + ",\n");
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void importUsers() {
		users = exporter.readUsers(inputFilePath);
	}

	public List<User> getUsers() {
		return users;
	}

	// helper method
	public void generateUsers(String filePath, int nrOfUsersToGenerate) {
		List<User> users = new ArrayList<>();
		for (int i = 1; i <= nrOfUsersToGenerate; i++) {
			users.add(new User("User" + i));
		}
		exporter.export(users, filePath);
	}
}
