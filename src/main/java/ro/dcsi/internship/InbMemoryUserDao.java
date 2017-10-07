package ro.dcsi.internship;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class InbMemoryUserDao implements IterableUserDao {
  Map<String, ExtendedUser> users;

  @Override
  public Iterator<ExtendedUser> read() {
    return users.values().iterator();
  }

  @Override
  public void write(Iterator<ExtendedUser> users) {
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
