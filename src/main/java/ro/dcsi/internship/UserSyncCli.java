package ro.dcsi.internship;

import java.util.Optional;

import org.apache.commons.cli.*;

public class UserSyncCli {
  public static void main(String[] args) {
    CommandLineParser parser = new DefaultParser();

    Options options = new Options();
    Option forgerockOption = Option.builder("f").hasArg(true).argName("host username password").longOpt("forgerock")
        .numberOfArgs(3).valueSeparator(' ').build();
    Option csvOption = Option.builder("c").hasArg(true).argName("file").longOpt("csv").numberOfArgs(1).build();
    options.addOption(forgerockOption);
    options.addOption(csvOption);

    try {
      CommandLine cmd = parser.parse(options, args);
      Option[] parserOptions = cmd.getOptions();
      if(parserOptions.length==2){
        Optional<UserDao> dao1 = extractDao(parserOptions[0]);
        Optional<UserDao> dao2 = extractDao(parserOptions[1]);
        if(dao1.isPresent() && dao2.isPresent()){
          new UserSyncApp().exportAndLogAnyException(dao1.get(),dao2.get());
        }else{
          helpInfo(options); 
        }
      } else {
        helpInfo(options);
      }

    } catch (ParseException e) {
      throw new WrappedCheckedException(e);
    }
  }

  private static Optional<UserDao> extractDao(Option option) {
    if (option.getLongOpt().equals("forgerock")) {
      return Optional.of(new ForgerockUserDao2(option.getValue(0), option.getValue(1), option.getValue(2)));
    } else if (option.getLongOpt().equals("csv")) {
      return Optional.of(new BeanBasedUserDaoAdapter(new BeanBasedUserDao(), option.getValue()));
    }
    return Optional.empty();
  }

  private static void helpInfo(Options givenOps) {
    HelpFormatter help = new HelpFormatter();
    help.printHelp("usersync +", givenOps);
  }

}
