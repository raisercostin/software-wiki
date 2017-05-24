package ro.dcsi.internship;

public class User {
	public static String username;
	public static String email;
	public static String other;

	public User(String username, String email, String other) {
		User.username = username;
		this.email = email;
		this.other = other;
	}
	public User(String username, String email) {
		this.username = username;
		this.email = email;
		
	}
	public String getUsername() {
	    return User.username;
	}
	public String getEmail() {
	    return User.email;
	}
	public String getOther() {
		    return User.other;
	}
	public void setOther(String other) {
	    User.other=other;
	}
	public void setEmail(String email) {
	    User.email=email;
	}
	public void setUsername(String name) {
	    User.username=name;
	}
	
}
