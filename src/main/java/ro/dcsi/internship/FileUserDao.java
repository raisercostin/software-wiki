package ro.dcsi.internship;

import java.util.List;

public interface FileUserDao {
  List<User> load(String fileName);
  void save(List<User> users, String fileName);
  String loadHeader(String string);
}
