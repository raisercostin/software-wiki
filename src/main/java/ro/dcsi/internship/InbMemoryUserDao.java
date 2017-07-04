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

  @Override
  public Optional<User> getUser(String id) {
    // TODO Auto-generated method stub
    throw new RuntimeException("Not Implemented Yet!!!");
  }

  @Override
  public boolean deleteUser(String id) {
    // TODO Auto-generated method stub
    throw new RuntimeException("Not Implemented Yet!!!");
  }

  @Override
  public boolean updateUser(User user) {
    // TODO Auto-generated method stub
    throw new RuntimeException("Not Implemented Yet!!!");
  }

  @Override
  public boolean addUser(User user) {
    // TODO Auto-generated method stub
    throw new RuntimeException("Not Implemented Yet!!!");
  }

}
