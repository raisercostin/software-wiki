package ro.dsci.internship;

import org.junit.Test;

public class UserSyncAppTestVlad {

  @Test
  public void test() {
    // copieze toti userii din fisier.csv in users-all
    UserSyncApp.main("--operation", "copytofile1", "--csv", "src/test/resources/fisier.csv", "--csvWrite",
        "target/users-all-vlad.csv");
    // adauge noii user in users-all
    UserSyncApp.main("--operation", "copytofile2", "--csv", "src/test/resources/fisier2.csv", "--csvWrite",
        "target/users-all-vlad.csv");
    // copieze toti userii din fisier.csv in users-all
    // UserSyncApp.main("--csv","src/test/resources/fisier.csv","--forgerock","http://localhost:8080","openidm-admin","openidm-admin");
    // copieze toti userii din fisier.csv in users-all
    // UserSyncApp.main("--forgerock","http://localhost:8080","openidm-admin","openidm-admin","--csv","target/users-all-from-forgerock.csv");
  }
}