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

public class VladUserDao {

	public static void main(String[] args) {
		List<User> users = readUsersFromCSV("users.csv");
		for (User b : users) {
			System.out.println(b);
		}

	}

	private static List<User> readUsersFromCSV(String fileName) {
		List<User> users = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				User user = createUser(attributes, getHeader(pathToFile));
				users.add(user);
				line = br.readLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return users;
	}

	private static Map<String, Integer> getHeader(Path pathToFile) {
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

	private static User createUser(String[] metadata, Map<String, Integer> header) {
		String nume = metadata[header.get("Nume")];
		String prenume = metadata[header.get("Prenume")];
		String email = metadata[header.get("Email")];

		return new User(nume, prenume, email);
	}
	
	public void WriteUsers(List<User> users, String locatie) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not Implemented Yet!!!");
	}

}
