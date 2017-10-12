package ro.dsci.internship;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class UserSyncAppGabi {
  static GabrielUserDao dao = new GabrielUserDao();
  static UnirestForgeRockUserDao adminDao = new UnirestForgeRockUserDao();
  static List<User> tempLocali;
  static List<User> tempServer;

  public static void main(String... args) {

    Options options = new Options();
    options.addOption("csv", true, "Citeste CSV din fisier in o lista");
    options.addOption("csvWrite", true, "Scrie CSV din lista in un fisier");
    options.addOption("csvUpdate", true, "update cu CSV din lista in un fisier/update nu delete");
    options.addOption("forgerock", true, "Citeste CSV din server in un fisier" + "Dati adresa serverului va rog");
    options.addOption("user", true, "Citeste CSV din server in un fisier" + "Dati parola va rog");
    options.addOption("forgerockWrite", true,
        "Scie CSV citit anterior de pe server in un fisier" + "zice-ti unde sa punem CSV");

    try {
      CommandLine line = new BasicParser().parse(options, args);

      if (line.hasOption("csv")) {
        String v = line.getOptionValue("csv");
        tempLocali = dao.readUsers(v);
      }

      if (line.hasOption("csvWrite")) {
        String v = line.getOptionValue("csvWrite");
        dao.writeUsers(tempLocali, v);
      }

      if (line.hasOption("csvUpdate")) {
        String v = line.getOptionValue("csvUpdate");
        dao.updateUsers(tempLocali, v);
      }
      if (line.hasOption("forgerock")) {
        String v = line.getOptionValue("forgerock");
        adminDao.url = "v";
      }

      if (line.hasOption("user")) {
        String v = line.getOptionValue("user");
        adminDao.userLogIn = "v";
        tempServer = adminDao.readUsers(v);
      }

      if (line.hasOption("forgerockWrite")) {
        String v = line.getOptionValue("forgerockWrite");
        adminDao.writeUsers(tempServer, v);
      }

    } catch (ParseException e) {

      throw new RuntimeException(e);
    }

  }
  /*
   *  List<User> readUsers(String locatie);
  
  void writeUsers(List<User> users, String locatie);
  
   * 
     //copieze toti userii din fisier.csv in users-all
    UserSyncApp.main("--csv","src/test/resources/CVSTest.csv","--csvWrite","target/users-all.csv");
    //adauge noii user in users-all
    UserSyncApp.main("--csv","src/test/resources/fisier2.csv","--csvWrite","target/users-all.csv");
    //copieze toti userii din fisier.csv in users-all
    UserSyncApp.main("--csv","src/test/resources/fisier.csv","--forgerock","http://localhost:8080","--user","openidm-admin");
    //copieze toti userii din fisier.csv in users-all
    UserSyncApp.main("--forgerock","http://localhost:8080","-
    *
    */

}
