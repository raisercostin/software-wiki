package ro.dcsi.internship;

public class UserSync {
  public void copyUsers(IterableUserDao src, IterableUserDao dest) {
    dest.write(src.read());
  }
}
