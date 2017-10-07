package ro.dsci.internship;

public class User {
	public final String username;
	public final String firstname;
	public final String lastname;
	public final String email;

	public User(String usernname, String firstname, String lastname, String email) {
		this.username = usernname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [Username=" + username + ", prenume=" + firstname + ", nume=" + lastname + ", email=" + email
				+ "]";
	}

	public String toString2() {
		return username + "," + firstname + "," + lastname + "," + email;
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		if (this.username == user.username && this.firstname == user.firstname && this.lastname == user.lastname
				&& this.email == user.email) {
			return true;
		}
		return false;

	}

	

}
