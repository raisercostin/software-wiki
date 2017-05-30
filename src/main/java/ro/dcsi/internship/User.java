package ro.dcsi.internship;

import java.util.Set;

public class User {
	public final String username;
	public final String email;
	public final String firstname;
	public final String lastname;

	private Set<String> permisions;
	public User(String username, String email, String firstname, String lastname) {
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public User(String username) {
		this.username = username;
		this.email = "NoEmail";
		this.firstname = "NoFirstName";
		this.lastname = "NoLastName";	
	}
	public User(String username, String email) {
		this.username = username;
		this.email = email;
		this.firstname = "NoFirstName";
		this.lastname = "NoLastName";		
	}
	@Override
	public String toString() {
		return "User("+username+"<"+email+">)";
	}
}
