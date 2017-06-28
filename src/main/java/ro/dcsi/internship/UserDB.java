package ro.dcsi.internship;

public interface UserDB extends Iterable<User> {
	public User getUser(String id);
}
