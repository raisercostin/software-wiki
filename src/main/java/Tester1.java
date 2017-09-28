import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Tester1 {

	public static void main(String[] args) {
		List<User> users = readUsersFromCSV("users.txt");
		for(User b : users) {
			System.out.println(b);
		}

	}
	
	private static List<User> readUsersFromCSV(String fileName){
		List<User> users = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try(BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)){
			String line = br.readLine();
			while(line != null) {
				String[] attributes = line.split(",");
				User user = createUser(attributes);
				users.add(user);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return users;
	}
	
	private static User createUser(String[] metadata) {
		String name = metadata[0];
		int age = Integer.parseInt(metadata[1]);
		String username = metadata[2];
		
		return new User(name, age, username);
	}

}
