package ro.dsci.internship;

import java.util.List;

public interface UserDao {
  List<User> readUsers(String location);

  void writeUsers(List<User> users, String location);

  default void updateUsers(List<User> users, String location){
    List<User> oldUsers = readUsers(location);
    oldUsers.addAll(users);
    writeUsers(oldUsers,location);
  }
}
