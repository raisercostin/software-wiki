package ro.dcsi.internship;

public interface UserDB extends Iterable<User> {
  public boolean userExists(String id);

  public User getUser(String id);

  public boolean deleteUser(String id);

  public boolean updateUser(User user);
}
