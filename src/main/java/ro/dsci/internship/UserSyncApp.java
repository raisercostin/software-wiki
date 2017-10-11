package ro.dsci.internship;

import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class UserSyncApp {

  @Parameter(names = { "--csv", "-r" })
  private static String readFromFile;

  @Parameter(names = { "--csvWrite", "-w" })
  private static String writeToFile;

  public static void main(String... args) {

    UserSyncApp userSyncApp = new UserSyncApp();
    
    JCommander.newBuilder()
    .addObject(userSyncApp)
    .build()
    .parse(args);

    VladUserDao userDao = new VladUserDao();
    List<User> users = userDao.readUsers(readFromFile);
    userDao.writeUsers(users, writeToFile);

  }
}
