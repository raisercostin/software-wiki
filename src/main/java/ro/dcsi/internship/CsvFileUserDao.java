package ro.dcsi.internship;

import java.io.*;
import java.util.*;

import com.google.common.base.Preconditions;

public class CsvFileUserDao implements UserDao {
	public CsvFileUserDao() {
		System.out.println("instantiated");
	}

	@Override
	public String loadHeader(String csvFile) {
		String csvSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line = "";
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> load(String csvFile) {
		String csvSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			ArrayList<User> users = new ArrayList<>();
			String line = "";
			Header header = new Header(br.readLine().split(csvSplitBy));
			while ((line = br.readLine()) != null) {
				String[] splited = line.split(csvSplitBy);
				users.add(new User(header.getUserName(splited), header.getEmail(splited)));
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(List<User> users, String outputFileName) {
		Header header = new Header();
		try (FileWriter fo = new FileWriter(outputFileName); BufferedWriter out = new BufferedWriter(fo)) {
			out.write(header.fullHeader+"\n");
			for (User user : users) {
				out.write(Arrays.toString(header.fromUser(user)) + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static class Header {
		public static String fullHeader = "username,email,firstname,lastname";
		private final Map<String, Integer> header;

		public Header() {
			this(fullHeader.split(","));
		}

		public Header(String[] columns) {
			header = new TreeMap<>();
			int index = 0;
			for (String columnName : columns) {
				String key = columnName.trim().toLowerCase();
				header.put(key, new Integer(index));
				index++;
			}
			Preconditions.checkState(header.get("email")!=null,
					"Header should contain column [email]. Shouldn't be quoted. It was just " + header.keySet());
			Preconditions.checkState(header.containsKey("username"),
					"Header should contain [username]. Shouldn't be quoted. It was just " + header);
		}

		public String getEmail(String[] splited) {
			return splited[header.get("email")];
		}

		public String getUserName(String[] splited) {
			return splited[header.get("username")];
		}
		
		public String getFirstName(String[] splited) {
			return splited[header.get("firstname")];
		}
		
		public String getLastName(String[] splited) {
			return splited[header.get("lastname")];
		}

		public User toUser(String[] splited) {
			return new User(getUserName(splited), getEmail(splited), getFirstName(splited), getLastName(splited));
		}

		public String[] fromUser(User user) {
			return new String[]{user.username, user.email, user.firstname, user.lastname};
		}

		public String[] headerValues() {
			return new String[]{"username", "email", "firstname", "lastname"};
		}
	}
}
