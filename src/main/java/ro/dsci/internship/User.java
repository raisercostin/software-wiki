package ro.dsci.internship;

public class User {
	public String id;
	public String username;
	public String firstname;
	public String lastname;
	public String email;

	public User(String id, String usernname, String firstname, String lastname, String email) {
		this.id = id;
		this.username = usernname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [Id =" + id + ", Username=" + username + ", Prenume=" + firstname + ", Nume=" + lastname + ", Email=" + email
				+ "]";
	}

	public String toString2() {
		return id + "," + username + "," + firstname + "," + lastname + "," + email;
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		if (this.id == user.id && this.username == user.username && this.firstname == user.firstname && this.lastname == user.lastname
				&& this.email == user.email) {
			return true;
		}
		return false;

	}

	

}
