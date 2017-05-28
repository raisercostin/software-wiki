package ro.dcsi.internship;

import java.util.Set;

public class User {
	public final String username;
	public final String email;
	public final String other;
	private Set<String> permisions;
	public User(String username, String email, String other) {
		this.username = username;
		this.email = email;
		this.other = other;
	}
	public User(String username) {
		this.username = username;
		this.email = "todo";
		this.other = null;		
	}
	public User(String username, String email) {
		this.username = username;
		this.email = email;
		this.other = null;		
	}
}
