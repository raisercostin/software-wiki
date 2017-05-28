package ro.dcsi.internship;

import java.io.*;
import java.util.*;

import com.google.common.base.Preconditions;

public class CsvExporter {
	public CsvExporter() {
		System.out.println("instantiated");
	}

	public void export(String inputFileName, String outputFileName) {
		List<User> users = readUsers(inputFileName);
		export(users, outputFileName);
	}

	public void export(List<User> users, String outputFileName) {
		try (FileWriter fo = new FileWriter(outputFileName); BufferedWriter out = new BufferedWriter(fo)) {
			out.write("username,email,others");
			for (User user : users) {
				out.write(user.username + "," + user.email + "," + user.other + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readHeading(String fileName) {
		ArrayList<User> users = new ArrayList<User>();
		String s = new String();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			s = br.readLine();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return s;
	}

	public List<User> readUsers(String csvFile) {
		ArrayList<User> users = new ArrayList<>();
		String csvSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line = "";
			Header header = new Header(br.readLine().split(csvSplitBy));
			while ((line = br.readLine()) != null) {
				String[] splited = line.split(csvSplitBy);
				users.add(new User(header.getName(splited), header.getEmail(splited), line));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return users;
	}

	private static class Header {
		private final Map<String, Integer> header;

		public Header(String[] columns) {
			header = new TreeMap<>();
			int index = 0;
			for (String columnName : columns) {
				header.put(columnName.trim().toLowerCase(), index);
				index++;
			}
			Preconditions.checkState(header.containsKey("email"),
					"Header should contain [email]. It was just " + header);
			Preconditions.checkState(header.containsKey("username"),
					"Header should contain [username]. It was just " + header);
		}

		public String getEmail(String[] splited) {
			return splited[header.get("email")];
		}

		public String getName(String[] splited) {
			return splited[header.get("username")];
		}
	}

	public List<User> readUsersWithoutHeader(String fileName) {
		List<User> users = new ArrayList<>();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				String[] splited = line.split(",");
				users.add(new User(splited[0], splited[1], splited[2]));
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
