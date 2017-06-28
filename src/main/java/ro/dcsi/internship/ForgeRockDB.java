package ro.dcsi.internship;

import java.util.Iterator;

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
}
