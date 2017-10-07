package ro.dcsi.internship;

import java.util.List;

public interface UserDao {
  void save(User... users);
  List<User> load();
}
