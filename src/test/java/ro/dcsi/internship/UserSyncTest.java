package ro.dcsi.internship;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserSyncTest {
  private static String openIDMServer = "http://localhost:8080";
  private static String openIDMUsername = "openidm-admin";
  private static String openIDMPassword = "openidm-admin";
  
  @Test
  public void csvBackupTest() {
    CsvExporter exporter = new CsvExporter("src/test/resources/CSV/csvBackupTest.csv");
    ForgeRockDB db = new ForgeRockDB(openIDMServer, openIDMUsername, openIDMPassword);
    
    exporter.export(db.iterator());
  }
}
