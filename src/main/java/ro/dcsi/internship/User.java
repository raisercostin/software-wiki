package ro.dcsi.internship;

public class User {
	public final String username;
	public final String email;
	public final String firstName;
	public final String lastName;
	private final Permissions permissions;

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
		this.permissions = new Permissions();
	}

	public User(String username) {
		this.username = username;
		this.email = "";
		this.firstName = "";
		this.lastName = "";
		this.permissions = new Permissions();
	}
	
	public User(User user) {
		this.username = user.username;
		this.email = user.email;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.permissions = new Permissions(user.permissions);
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
