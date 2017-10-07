package ro.dcsi.internship;

import java.util.Arrays;
import java.util.List;

public interface UserDao {
  default void save(User... users){
    save(Arrays.asList(users));
  }
  void save(List<User> users);
  List<User> load();
}