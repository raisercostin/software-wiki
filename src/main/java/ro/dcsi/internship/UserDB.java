package ro.dcsi.internship;

import java.util.Optional;

public interface UserDB extends Iterable<User> {
  public boolean userExists(String id);

  public Optional<User> getUser(String id);

  public boolean deleteUser(String id);

  public boolean updateUser(User user);
  
  public boolean addUser(User user);
}
