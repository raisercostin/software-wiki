package ro.dcsi.internship;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class InbMemoryUserDao implements UserDao {
  Map<String, User> users;

  @Override
  public Iterator<User> read() {
    return users.values().iterator();
  }

  @Override
  public void write(Iterator<User> users) {
    //for (User u : users) {
    //  addUser(u);
    //}
  }

  @Override
  public boolean userExists(String id) {
    // TODO Auto-generated method stub
    throw new RuntimeException("Not Implemented Yet!!!");
  }
}
