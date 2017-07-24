package ro.dcsi.internship;

import java.util.List;

public interface UserDaoInterface {
  void writeUsers(String file, User... users);

  List<User> readUsers(String file);
}
