package ro.dcsi.internship;

public class UserSyncApp {
  public void export(UserDao srcDao, UserDao dstDao) {
    dstDao.writeUsers(srcDao.readUsers().toArray(new TheUser[0]));
  }
}
