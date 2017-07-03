package ro.dcsi.internship;

public class UserSync {
  public void copyUsers(UserDao src, UserDao dest) {
    dest.write(src.read());
  }
}
