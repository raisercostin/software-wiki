package ro.dcsi.internship;

public class User {

	public String name;

	public User(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}
