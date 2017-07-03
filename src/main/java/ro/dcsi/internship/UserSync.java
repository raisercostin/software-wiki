package ro.dcsi.internship;

import java.util.Iterator;

public interface UserSync {
  public Iterator<User> readUsers();
}