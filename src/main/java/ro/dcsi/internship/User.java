package ro.dcsi.internship;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

public class User {
	public final String username;
	private String email = null;
	private String firstName = null;
	private String lastName = null;
	private Permissions permissions = new Permissions();

	public User(String username, String email, String firstName, String lastName,
			Permissions permissions) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.permissions = new Permissions(permissions);
	}
	
	public User(String username, String email, String firstName, String lastName,
			String permissions) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.permissions = new Permissions(permissions);
	}

	public User(String username, String email, String firstName, String lastName) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String username) {
		this.username = username;
	}
	
	public User(User user) {
		this.username = user.username;
		this.email = user.email;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.permissions = new Permissions(user.permissions);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean hasPermission(String permission) {
		return permissions.hasPermission(permission);
	}

	public void addPermission(String permission) {
		permissions.addPermission(permission);
	}

	public void removePermission(String permission) {
		permissions.removePermission(permission);
	}

	public void setPermission(String permission, boolean val) {
		permissions.setPermission(permission, val);
	}
	
	public String toString() {
		return this.username + ": [" + this.getEmail() + ", " + this.getFirstName() + ", " + this.getLastName() + ", " + this.permissions + "]";
	}
}
