package ro.dcsi.internship;
import org.apache.commons.cli.*;

public class UserSyncApp {

  //App name
  private final static String appName="usersync";
  private final static String usage = appName + " <src> <dest>";

  //Option strings
  private final static String forgeRockShortOption="f";
  private final static String forgeRockLongOption="forgerock";
  private final static String csvShortOption="c";
  private final static String csvLongOption="csv";


  //Print help
  private static void printHelp(Options options){
    //Print Help
    HelpFormatter help = new HelpFormatter();
    help.printHelp(usage,options);
  }

  private static UserDao parseDaoInfo(Option daoInfo){
    throw new RuntimeException("Not implemented yet!");
  }


  public static void main(String[] args) {

    //Create option groupe
    Options options = new Options();

    //Creating options
    Option forgeRock = Option.builder(forgeRockShortOption)
            .hasArg(true)
            .argName("server addr> <username> <password")
            .longOpt(forgeRockLongOption)
            .desc("Option to specify forgeRock server")
            .numberOfArgs(3)
            .valueSeparator(' ')
            .build();
    Option csv = Option.builder(csvShortOption)
            .hasArg(true)
            .argName("file")
            .longOpt(csvLongOption)
            .desc("Option to Specify CSV file")
            .numberOfArgs(1)
            .valueSeparator(' ')
            .build();

    //Add options to group
    options.addOption(forgeRock);
    options.addOption(csv);

    //Create Necesary stuff
    CommandLine cmd;
    CommandLineParser parser = new DefaultParser();
    UserDao src;
    UserDao dest;

    //Parse input
    try {
      cmd = parser.parse(options, args);


      //Get options
      Option parsedOptions[] = cmd.getOptions();

      //check number of option
      if(parsedOptions.length == 2){

        //Parse first option
        src = parseDaoInfo(parsedOptions[0]);

        //Parse second option
        dest = parseDaoInfo(parsedOptions[1]);


        //TODO Implement App actual logic

      }

      printHelp(options);

    }
    catch (ParseException e){
      printHelp(options);
      throw new RuntimeException("Invalid Arguments",e);
    }




    //create src/dest UserDao
    //call UserSync
  }
}
