package ro.dcsi.internship;

public class User {
	public String username;
	public String email;
	public String other;

	public User(String username, String email, String other) {
		this.username = username;
		this.email = email;
		this.other = other;
	}
	public User(String username, String email) {
		this.username = username;
		this.email = email;
		
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
	public void setOther(String other) {
	    this.other=other;
	}
	public void setEmail(String email) {
	    this.email=email;
	}
	public void setUsername(String name) {
	    this.username=name;
	}
	
}
