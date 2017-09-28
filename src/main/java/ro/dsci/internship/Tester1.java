package ro.dsci.internship;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Tester1 implements UserDao {

	public static void main(String[] args) {
		List<User> users = new Tester1().readUsers("users.txt");
		for(User b : users) {
			System.out.println(b);
		}

	}

  @Override
  public List<User> readUsers(String fileName) {
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
		String prenume = metadata[1];
		String username = metadata[2];
		
		return new User(name, prenume, username);
	}

  @Override
  public void writeUsers(List<User> users, String locatie) {
    // TODO Auto-generated method stub
    throw new RuntimeException("Not Implemented Yet!!!");
  }
}
