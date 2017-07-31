package ro.dcsi.internship;

import java.util.List;

public interface UserDao {
  void writeUsers(TheUser... users);

  List<TheUser> readUsers();
}
