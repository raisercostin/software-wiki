package ro.dcsi.internship;

import java.util.Iterator;
import java.util.Optional;

public interface IterableUserDao extends Iterable<ExtendedUser>, UserReader, UserWriter {
  /**Javadoc comment.
   * Should return as fast as possible without iterating over all users.*/
//  /*internal comment*/
  public boolean userExists(String id);
//
//  public Optional<User> getUser(String id);
//
//  public boolean deleteUser(String id);
//
//  public boolean updateUser(User user);
//
//  public boolean addUser(User user);

  @Override
  default public Iterator<ExtendedUser> iterator() {
    return read();
  }
}
