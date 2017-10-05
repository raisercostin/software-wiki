package ro.dsci.internship;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GabrielUserDao implements UserDao {
	List<String> headerList;
	
	@Override
	public List<User> readUsers(String locatie) {
		List<User> lista = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(locatie);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);) {
			

			String headerLine = br.readLine();
			String[] header = headerLine.split(",");
			 headerList = Arrays.asList(header);
			// Gasim indexul coloanei cu numele X
			int indexUsername = headerList.indexOf("username");
			int indexFirstName = headerList.indexOf("firstname");
			int indexLastName = headerList.indexOf("lastname");
			int indexEmail = headerList.indexOf("email");
			
			String line;
			while ((line = br.readLine()) != null) {
				String[] userDetails = line.split(",");
				String username = userDetails[indexUsername];
				String firstname = userDetails[indexFirstName];
				String lastname = userDetails[indexLastName];
				String email = userDetails[indexEmail];
				User user = new User(username, firstname, lastname, email);
				lista.add(user);
				System.out.println(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Implemented Yet!!!");
		}
		return lista;

	}

	@Override
	public void writeUsers(List<User> users, String locatie) {

		try (PrintStream out = new PrintStream(locatie)) {
			out.print(headerList.get(0)+ "," +headerList.get(1)+ ","
					+headerList.get(2)+"," + headerList.get(3)+ "\n"
					);

			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				out.print(user.username + "," + user.firstname + "," + user.lastname + "," + user.email + "\n");
			}

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
