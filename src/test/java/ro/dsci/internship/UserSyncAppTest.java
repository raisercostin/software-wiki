package ro.dsci.internship;

import org.junit.Test;

public class UserSyncAppTest {

  @Test
  public void test() {
    //copieze toti userii din fisier.csv in users-all
    UserSyncApp.main("--csv","src/test/resources/fisier.csv","--csvWrite","target/users-all.csv");
    //adauge noii user in users-all
    UserSyncApp.main("--csv","src/test/resources/fisier2.csv","--csvWrite","target/users-all.csv");
    //copieze toti userii din fisier.csv in users-all
    UserSyncApp.main("--csv","src/test/resources/fisier.csv","--forgerock","http://localhost:8080","--user","openidm-admin");
    //copieze toti userii din fisier.csv in users-all
    UserSyncApp.main("--forgerock","http://localhost:8080","--user","openidm-admin","--csv","target/users-all-from-forgerock.csv");
  }
}
