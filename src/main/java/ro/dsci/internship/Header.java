package ro.dsci.internship;

import java.util.Map;
import java.util.TreeMap;

import com.google.common.base.Preconditions;

public class Header {
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

	public String getEmail(String[] values) {
		return values[header.get("email")];
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