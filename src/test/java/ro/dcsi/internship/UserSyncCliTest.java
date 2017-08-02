package ro.dcsi.internship;

import org.junit.Test;

public class UserSyncCliTest {

  @Test
  public void test() {
    UserSyncCli.main(new String[] { "--forgerock", "http://dcs-xps:8080", "openidm-admin", "openidm-admin", "--csv",
        "target/export2.csv" });
  }

  @Test(expected = NullPointerException.class)
  public void testAppDirectly() {
    new UserSyncApp().export(null, null);
  }
}
