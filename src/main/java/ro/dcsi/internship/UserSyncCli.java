package ro.dcsi.internship;

import org.apache.commons.cli.*;

public class UserSyncCli {
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        Option forgerockOption = Option.builder("f").hasArg(true).argName("host username password").longOpt("forgerock").numberOfArgs(3).valueSeparator(' ').build();
        Option csvOption = Option.builder("c").hasArg(true).argName("file").longOpt("csv").numberOfArgs(1).build();
        options.addOption(forgerockOption);
        options.addOption(csvOption);
        
        try {
            CommandLine cmd = parser.parse(options, args);
            Option[] parserOptions = cmd.getOptions();
            if ((cmd.hasOption("f") || cmd.hasOption("forgerock")) && ((cmd.hasOption("c") || cmd.hasOption("csv")))) {
              new UserSyncApp().export(new ForgerockUserDao(parserOptions[0].getValue(0), parserOptions[0].getValue(1), parserOptions[0].getValue(2)), new BeanBasedUserDaoAdapter(new BeanBasedUserDao(), parserOptions[1].getValue()));
            }  else {
              helpInfo(options);
            }
            
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void helpInfo(Options givenOps) {
      HelpFormatter help = new HelpFormatter();
      help.printHelp("usersync +", givenOps);
    }

   
}
