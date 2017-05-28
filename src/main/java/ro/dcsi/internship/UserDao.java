package ro.dcsi.internship;

import java.util.List;

public interface UserDao {
	String loadHeader(String csvFile);
	List<User> load(String csvFile);
	void save(List<User> users, String outputFileName);
}