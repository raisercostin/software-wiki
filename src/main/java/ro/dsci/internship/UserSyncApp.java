package ro.dsci.internship;

import java.util.List;

import org.raisercostin.jedi.Locations;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class UserSyncApp {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserSyncApp.class);


  public static class UserSyncParameters{
    @Parameter(names = { "--operation", "-op" }, required=true)
    public String operation;
  
    @Parameter(names = { "--csv", "-r" })
    public String readFromFile;
  
    @Parameter(names = { "--csvWrite", "-w" })
    public String writeToFile;
  
    @Parameter(names = { "--forgerock" })
    public String serverLink;
  
    @Parameter(names = { "--user" })
    public String userpass;
  }

  private static UserSyncParameters extractParams(String... args) {
    UserSyncParameters params = new UserSyncParameters();
    JCommander.newBuilder().addObject(params).build().parse(args);
    return params;
  }

  public static void main(String... args) {
    try {
      VladUserDao userDao = new VladUserDao();
      UnirestForgeRockUserDao forgeUser = new UnirestForgeRockUserDao();

      UserSyncParameters params = extractParams(args);

      if (params.operation.equals("copytofile1")) {
        List<User> users = userDao.readUsers(params.readFromFile);
        userDao.writeUsers(users, params.writeToFile);
      }

      if (params.operation.equals("copytofile2")) {
        List<User> users = userDao.readUsers(params.writeToFile);
        userDao.sumOfUsers(params.readFromFile, users);
        userDao.writeUsers(users, params.writeToFile);
      }

      if (params.operation.equals("copytoserver")) {
        List<User> users = userDao.readUsers(params.readFromFile);
        forgeUser.url = params.serverLink;
        forgeUser.userLogIn = params.userpass;
        forgeUser.writeUsers(users, "");
      }

      if (params.operation.equals("copyfromserver")) {
        forgeUser.url = params.serverLink;
        forgeUser.userLogIn = params.userpass;
        List<User> users = forgeUser.readUsers("");
        userDao.writeUsers(users, params.writeToFile);
      }
    } catch (Throwable e) {
      logger.warn("Really I couldn't handle this",e);
    }
  }
}
