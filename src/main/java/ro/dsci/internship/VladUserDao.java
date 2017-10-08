package ro.dsci.internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VladUserDao implements UserDao {

	public static void main(String[] args) {
		List<User> users = new VladUserDao().readUsers("users.csv");
		for (User b : users) {
			System.out.println(b);
		}
	}

	@Override
	public void writeUsers(List<User> users, String locatie) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Implemented Yet!!!");
	}

	@Override
	public List<User> readUsers(String fileName) {
		List<User> users = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		Map<String, Integer> header = getHeader(pathToFile);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();// ignore header
			line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				User user = createUser(attributes, header);
				users.add(user);
				line = br.readLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return users;
	}

	private Map<String, Integer> getHeader(Path pathToFile) {
		Map<String, Integer> map = new HashMap<>();
		try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
			String firstLine = br.readLine();
			if (firstLine == null)
				throw new IOException("The file: " + pathToFile + " is empty!");
			final List<String> columns = Arrays.asList(firstLine.split(","));
			for (int i = 0; i < columns.size(); i++) {
				map.put(columns.get(i), i);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return map;
	}

	private User createUser(String[] row, Map<String, Integer> header) {
		String id = row[header.get("Id")];
		String usern = row[header.get("Username")];
		String prenume = row[header.get("Firstname")];
		String nume = row[header.get("Name")];
		String email = row[header.get("Email")];

		return new User(id, usern, prenume, nume, email);
	}

}
