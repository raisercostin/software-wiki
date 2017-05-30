package ro.dcsi.internship;

import java.util.Map;
import java.util.TreeMap;

import com.google.common.base.Preconditions;

public class Header {
	public static String defaultHeader = "username,email,firstname,lastname";
	public static String[] defaultHeaderValues = defaultHeader.split(",");
	public static String[] mandatoryHeaderValues = "username,email".split(",");
	private final Map<String, Integer> header;

	public Header() {
		this(defaultHeader.split(","));
	}

	public Header(String[] columns) {
		header = new TreeMap<>();
		int index = 0;
		for (String columnName : columns) {
			String key = columnName.trim().toLowerCase();
			header.put(key, new Integer(index));
			index++;
		}
		for (String column : mandatoryHeaderValues) {
			Preconditions.checkState(header.get(column)!=null,
					"Header should contain column ["+column+"]. Shouldn't be quoted. It was just [" + header.keySet()+"]");
		}
		if(!header.containsKey("firstname"))
			header.put("firstname", header.get("username"));
		if(!header.containsKey("lastname"))
			header.put("lastname", header.get("username"));
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
		return defaultHeaderValues;
	}
}
