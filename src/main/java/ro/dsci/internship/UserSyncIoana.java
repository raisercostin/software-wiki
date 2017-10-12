package ro.dsci.internship;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.internal.Sets;

public class UserSyncIoana {

  @Parameter(names = { "-csv", "-r" }, description = "to read from csv")
  private static String readCsv;

  @Parameter(names = { "-csvWrite", "-w" }, description = "to write to csv")
  private static String writeCsv;

  @Parameter(names = { "-forgerock" }, description = "forgerock server")
  private static String server;

  @Parameter(names = { "-user" }, description = "password field")
  private static String password;

  public static void main(String[] args) {

    Sets settings = new Sets();
    new JCommander(settings, args); // simple one-liner
    // do the logic

  }
}
