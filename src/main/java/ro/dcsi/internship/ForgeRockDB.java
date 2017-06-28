package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class ForgeRockDB implements UserDB {
	public final String openIDMServer, openIDMUsername, openIDMPassword;

	public ForgeRockDB(String openIDMServer, String openIDMUsername, String openIDMPassword) {
		this.openIDMServer = openIDMServer;
		this.openIDMUsername = openIDMUsername;
		this.openIDMPassword = openIDMPassword;
	}

	public Iterator<User> iterator() {
		return new ForgeRockDBIterator(this);
	}

	public User getUser(String id) {
		HTTPRequest request = new HTTPRequest(this.openIDMServer + "/openidm/managed/user/" + id, "GET",
				this.authenticationHeader());
		String response = request.send();
		JSONObject jsonUser = new JSONObject(response);

		Map<String, String> attributes = new Hashtable<String, String>();

		for (String attr : jsonUser.toMap().keySet()) {
			if (jsonUser.get(attr) instanceof String) {
				attributes.put(attr, (String) jsonUser.get(attr));
			}
		}

		User user = new User(jsonUser.getString("_id"), attributes);

		return user;
	}

	public Map<String, String> authenticationHeader() {
		Map<String, String> header = new Hashtable<String, String>();
		header.put("X-OpenIDM-Username", this.openIDMUsername);
		header.put("X-OpenIDM-Password", this.openIDMPassword);
		return header;
	}
}
