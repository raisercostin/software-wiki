package ro.dcsi.internship;

public class UserSyncCli {
  public static void main(String[] args) {
    UserDao srcDao = null;
    UserDao dstDao = null;
    new UserSyncApp().export(srcDao,dstDao);
    throw new RuntimeException("Not Implemented Yet!!!");
  }
}
