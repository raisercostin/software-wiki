package ro.dsci.internship;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UserSyncGabiAppTest {

  @Test
  public void test1Gabi() {
    // copieze toti userii din fisier.csv in users-all
    UserSyncGabiApp.main("--csvRead", "src/test/resources/CVSTest.csv", "--csvWrite", "target/users-all.csv");
    Path p1 = Paths.get("target/users-all.csv");
    Assert.assertTrue(Files.exists(p1));
    GabrielFileUserDao dao = new GabrielFileUserDao();
    List<User> users1 = dao.readUsers("src/test/resources/CVSTest.csv");
    List<User> users2 = dao.readUsers("target/users-all.csv");
    Assert.assertEquals(users1, users2);

  }

  @Test
  public void testThatSecondDaoWriteUsersIsCalled() {
    // copieze toti userii din fisier.csv in users-all
    String[] args = { "--csvRead", "src/test/resources/CVSTest.csv", "--csvWrite", "target/users-all.csv" };
    new UserSyncGabiApp(new VladUserDao(), new UnirestForgeRockUserDao()).sync(args);
    Path p1 = Paths.get("target/users-all.csv");
    Assert.assertTrue(Files.exists(p1));
    GabrielFileUserDao dao = new GabrielFileUserDao();
    List<User> users1 = dao.readUsers("src/test/resources/CVSTest.csv");
    List<User> users2 = dao.readUsers("target/users-all.csv");
    Assert.assertEquals(users1, users2);

  }

  @Test
  public void test2Gabi() {
    // Copiem fisierul initial
    GabrielFileUserDao dao = new GabrielFileUserDao();
    UserSyncGabiApp.main("--csvRead", "src/test/resources/CVSTest.csv", "--csvWrite", "target/users-all.csv");
    List<User> listaInitiala = dao.readUsers("target/users-all.csv");
    int initial = listaInitiala.size();

    // adauge noii user in users-all
    UserSyncGabiApp.main("--csvRead", "src/test/resources/CVSTest.csv", "--csvUpdate", "target/users-all.csv");
    List<User> listaFinala = dao.readUsers("target/users-all.csv");
    int dupa = listaFinala.size();
    Assert.assertEquals(dupa, initial + 4);// sunt 4 useri acolo
  }

  @Test
  public void test3Gabi() {
    GabrielFileUserDao dao = new GabrielFileUserDao();
    UnirestForgeRockUserDao adminDao = new UnirestForgeRockUserDao();

    // citesc useri din CVS
    List<User> listaPC = dao.readUsers("src/test/resources/CVSTest.csv");

    List<User> listaServerinit = adminDao.readUsers("");

    // adaug useri pe server
    UserSyncGabiApp.main("--csvRead", "src/test/resources/CVSTest.csv", "--forgeRockConnect", "http://localhost:8080",
        "--user", "openidm-admin", "--forgerockWriteOnServer", "src/test/resources/CVSTest.csv");
    List<User> listaServerFinal = adminDao.readUsers("");
    Assert.assertTrue(listaServerFinal.size() == (listaPC.size() + listaServerinit.size()));
  }

  @Test
  public void test4Gabi() {
    GabrielFileUserDao dao = new GabrielFileUserDao();
    UnirestForgeRockUserDao adminDao = new UnirestForgeRockUserDao();
    List<User> listaServerinit = adminDao.readUsers("");
    // adaug useri din server in calculator
    UserSyncGabiApp.main("--forgeRockConnect", "http://localhost:8080", "--user", "openidm-admin",
        "--forgerockWritefromServer", "target/users-all-from-forgerock.csv");
    List<User> fromServerToPc = dao.readUsers("target/users-all-from-forgerock.csv");
    Assert.assertEquals(fromServerToPc.size(), listaServerinit.size());
    Assert.assertEquals(fromServerToPc, listaServerinit);
  }
}
