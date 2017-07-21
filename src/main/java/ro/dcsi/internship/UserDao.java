package ro.dcsi.internship;

import java.util.List;

public interface UserDao {
  void writeUsers(String fileName, TheUser... users);
  List<TheUser> readUsers(String fileName);
}
