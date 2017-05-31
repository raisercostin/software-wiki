package ro.dcsi.internship;

import java.util.List;

public interface UserDao {
	List<User> load(String csvFile);
	void save(List<User> users, String outputFileName);
	String loadHeader(String csvFile);
}