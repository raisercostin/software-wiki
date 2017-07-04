package ro.dcsi.internship;

import java.util.Iterator;
import java.util.Optional;

public interface UserDao extends Iterable<User>, UserReader, UserWriter {
  public boolean userExists(String id);

  public Optional<User> getUser(String id);

  public boolean deleteUser(String id);

  public boolean updateUser(User user);

  public boolean addUser(User user);

  @Override
  default public Iterator<User> iterator() {
    return read();
  }
}
