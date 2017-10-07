package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Catalin on 20/06/2017.
 */
public class TestCSVRead {

//  @Test
//  @Ignore("")
//  public void interlacedReads() {
//    CsvUserDao database = new CsvUserDao("src/test/resources/CSV/headersnotdefault.csv");
//    int c1 = 0; int c2 = 0;
//    for (User u1 : database) {
//      database.addUser(new User("a"));
//      for (User u2 : database) {
//        c2++;
//        database.deleteUser("a");
//        //database.addUser(new User("b"));
//      }
//      Assert.assertNotNull(database.getUser("a"));
//      c1++;
//    }
//    assertEquals(c1*c1,c2);
//  }
  
  @Test
  public void HeadersNotDefault() {
    CsvUserDao database = new CsvUserDao("src/test/resources/CSV/headersnotdefault.csv");
    Iterator<User> users = database.read();
    User buffer = null;

    while (users.hasNext()) {
      buffer = users.next();
    }

    assertNotEquals(buffer, null);

    assertEquals("CatalinLast", buffer.getAttributeValue("name"));
    assertEquals("catalinlast@yahoo.com", buffer.getAttributeValue("email"));
  }

  @Test
  public void HeadersLarge() {
    CsvUserDao database = new CsvUserDao("src/test/resources/CSV/headerslarge.csv");
    Iterator<User> users = database.read();
    User buffer = null;
    int count = 0;

    while (users.hasNext()) {
      buffer = users.next();
      count++;
    }

    assertEquals(count, 1070);
    assertNotEquals(buffer, null);
    assertEquals("CatalinLast", buffer.getAttributeValue("name"));
    assertEquals("catalinlast@yahoo.com", buffer.getAttributeValue("email"));
  }

}
