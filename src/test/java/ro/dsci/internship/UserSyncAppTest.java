package ro.dsci.internship;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UserSyncAppTest {
  static GabrielUserDao dao = new GabrielUserDao();

  @Test
  public void test1Gabi() {

    //copieze toti userii din fisier.csv in users-all
    UserSyncAppGabi.main("--csv", "src/test/resources/CVSTest.csv", "--csvWrite", "target/users-all.csv");

    Path p1 = Paths.get("target/users-all.csv");
    Assert.assertTrue(Files.exists(p1));
  }

  @Test
  public void test2Gabi() {
    //Copiem fisierul initial
    UserSyncAppGabi.main("--csv", "src/test/resources/CVSTest.csv", "--csvWrite", "target/users-all.csv");
    List<User> listaInitiala = dao.readUsers("target/users-all.csv");
    int initial = listaInitiala.size();

    //adauge noii user in users-all
    UserSyncAppGabi.main("--csv", "src/test/resources/CVSTest.csv", "--csvUpdate", "target/users-all.csv");
    List<User> listaFinala = dao.readUsers("target/users-all.csv");
    int dupa = listaFinala.size();
    Assert.assertEquals(dupa, initial + 4);// sunt 4 useri acolo
  }
  //copieze toti userii din fisier.csv in users-all
  // UserSyncApp.main("--csv","src/test/resources/fisier.csv","--forgerock","http://localhost:8080","--user","openidm-admin");
  //copieze toti userii din fisier.csv in users-all
  // UserSyncApp.main("--forgerock","http://localhost:8080","--user","openidm-admin","--csv","target/users-all-from-forgerock.csv");
}
