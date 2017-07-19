package ro.dcsi.internship;

import java.util.List;

public interface UserDao {
  void writeUsers(String file, TheUser... users);
  List<TheUser> readUsers(String file);
}
