package ro.dcsi.internship;

public class User {
	public final String username;
	public final String email;
	public final String other;

	public User(String username, String email, String other) {
		this.username = username;
		this.email = email;
		this.other = other;
	}
	public User(String username, String email) {
		this.username = username;
		this.email = email;
		this.other = null;		
	}
	public User(String[] split) {
		this.username = split[0];
		this.email = split[1];
		this.other = split[2];
	}
	public String getUsername() {
	    return username;
	}
	public String getEmail() {
	    return email;
	}
	public String getOther() {
		    return other;
	}
}
