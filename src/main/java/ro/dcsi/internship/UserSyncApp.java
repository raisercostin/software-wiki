package ro.dcsi.internship;

import org.apache.commons.cli.*;

public class UserSyncApp {
  public static void main(String[] args) {
    Option srcType = Option.builder("st").longOpt("source-type").hasArgs().argName("type")
        .desc("Type of the source. Can be \"csv\" (CSV file) or \"fr\" (ForgeRock database)")
        .required().build();
    Option srcServer = Option.builder("ss").longOpt("source-server").hasArgs().argName("url")
        .desc("Server address for the source").build();
    Option srcUser = Option.builder("su").longOpt("source-user").hasArgs().argName("user")
        .desc("Server username for the source").build();
    Option srcPass = Option.builder("sp").longOpt("source-password").hasArgs().argName("pass")
        .desc("Server password for the source").build();
    Option srcFile = Option.builder("sf").longOpt("source-file").hasArgs().argName("file")
        .desc("Csv file path for the source").build();
    Option destType = Option.builder("dt").longOpt("destination-type").hasArgs().argName("type")
        .desc("Type of the destination. Can be \"csv\" (CSV file) or \"fr\" (ForgeRock database")
        .required().build();
    Option destServer = Option.builder("ds").longOpt("destination-server").hasArgs().argName("url")
        .desc("Server address for the destination").build();
    Option destUser = Option.builder("du").longOpt("destination-user").hasArgs().argName("user")
        .desc("Server username for the destination").build();
    Option destPass = Option.builder("dp").longOpt("destination-password").hasArgs().argName("pass")
        .desc("Server password for the destination").build();
    Option destFile = Option.builder("df").longOpt("destination-file").hasArgs().argName("file")
        .desc("Csv file path for the destination").build();
    Options options = new Options();
    options.addOption(srcType).addOption(srcServer).addOption(srcUser).addOption(srcPass)
        .addOption(srcFile).addOption(destType).addOption(destServer).addOption(destUser)
        .addOption(destPass).addOption(destFile);

    UserDao src = null;
    UserDao dest = null;
    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine line = parser.parse(options, args);
      if (line.getOptionValue("st").equals("csv")) {
        if (!line.hasOption("sf")) {
          System.out.println("You must specify a source file name.");
        } else {
          src = new CsvUserDao(line.getOptionValue("sf"));
        }
      } else if (line.getOptionValue("st").equals("fr")) {
        if (!line.hasOption("ss") || !line.hasOption("su") || !line.hasOption("sp")) {
          System.out.println("You must specify a source server address, username and password.");
        } else {
          src = new ForgeRockUserDao(new OpenIdConfig(line.getOptionValue("ss"),
              line.getOptionValue("su"), line.getOptionValue("sp")));
        }
      }
      if (line.getOptionValue("dt").equals("csv")) {
        if (!line.hasOption("df")) {
          System.out.println("You must specify a destination file name.");
        } else {
          dest = new CsvUserDao(line.getOptionValue("df"));
        }
      } else if (line.getOptionValue("dt").equals("fr")) {
        if (!line.hasOption("ds") || !line.hasOption("du") || !line.hasOption("dp")) {
          System.out
              .println("You must specify a destination server address, username and password.");
        } else {
          dest = new ForgeRockUserDao(new OpenIdConfig(line.getOptionValue("ds"),
              line.getOptionValue("du"), line.getOptionValue("dp")));
        }
      }
      if (src != null && dest != null) {
        UserSync userSync = new UserSync();
        userSync.copyUsers(src, dest);
      }
    } catch (ParseException exp) {
      System.out.println("Parsing failed.  Reason: " + exp.getMessage());
    }
  }
}