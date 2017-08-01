package ro.dcsi.internship;

import org.apache.commons.cli.*;

public class UserSyncCli {
    public static void main(String[] args) {
        UserDao srcDao = null;
        UserDao dstDao = null;
        new UserSyncApp().export(srcDao, dstDao);


        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        Option forgerockOption = Option.builder("f").hasArg(true).argName("host username password").longOpt("forgerock").numberOfArgs(3).valueSeparator(' ').build();
        Option csvOption = Option.builder("c").hasArg(true).argName("file").longOpt("csv").numberOfArgs(1).build();
        options.addOption(forgerockOption);
        options.addOption(csvOption);

        if (args[0] == forgerockOption.getLongOpt() || args[0] == forgerockOption.getOpt()) {

        } else if (args[0] == csvOption.getLongOpt() || args[0] == csvOption.getOpt()) {

        }

        try {
            CommandLine commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
