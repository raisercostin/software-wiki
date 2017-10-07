package ro.dsci.internship;

import java.util.List;

public interface UserDao {
  List<User> readUsers(String locatie);
  void writeUsers(List<User> users, String locatie);
  
}
