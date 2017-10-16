package ro.dsci.internship;

import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class UserSyncApp {

  @Parameter(names = { "--operation", "-op" })
  private static String operation;

  @Parameter(names = { "--csv", "-r" })
  private static String readFromFile;

  @Parameter(names = { "--csvWrite", "-w" })
  private static String writeToFile;

  @Parameter(names = { "--forgerock" })
  private static String serverLink;

  @Parameter(names = { "--user" })
  private static String userpass;

  public static void main(String... args) {
    UserSyncApp userSyncApp = new UserSyncApp();
    VladUserDao userDao = new VladUserDao();
    UnirestForgeRockUserDao forgeUser = new UnirestForgeRockUserDao();

    JCommander.newBuilder().addObject(userSyncApp).build().parse(args);

    if (operation.equals("copytofile1")) {
      List<User> users = userDao.readUsers(readFromFile);
      userDao.writeUsers(users, writeToFile);
    }

    if (operation.equals("copytofile2")) {
      List<User> users = userDao.readUsers(writeToFile);
      userDao.sumOfUsers(readFromFile, users);
      userDao.writeUsers(users, writeToFile);
    }

    if (operation.equals("copytoserver")) {
      List<User> users = userDao.readUsers(readFromFile);
      forgeUser.url = serverLink;
      forgeUser.userLogIn = userpass;
      forgeUser.writeUsers(users, "");
    }

    if (operation.equals("copyfromserver")) {
      forgeUser.url = serverLink;
      forgeUser.userLogIn = userpass;
      List<User> users = forgeUser.readUsers("");
      userDao.writeUsers(users, writeToFile);
    }
  }
}
