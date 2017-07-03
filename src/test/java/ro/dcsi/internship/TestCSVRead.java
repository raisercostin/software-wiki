package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Iterator;

import org.junit.Test;

/**
 * Created by Catalin on 20/06/2017.
 */
public class TestCSVRead {



  @Test
  public void HeadersNotDefault() {
    CsvDB database = new CsvDB("src/test/resources/CSV/headersnotdefault.csv");
    Iterator<User> users = database.readUsers();
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
    CsvDB database = new CsvDB("src/test/resources/CSV/headerslarge.csv");
    Iterator<User> users = database.readUsers();
    User buffer = null;
    int count = 0;

    while (users.hasNext()) {
      buffer = users.next();
      System.out.println(buffer);
      count++;
    }

    assertEquals(count, 1070);
    assertNotEquals(buffer, null);
    assertEquals("CatalinLast", buffer.getAttributeValue("name"));
    assertEquals("catalinlast@yahoo.com", buffer.getAttributeValue("email"));
  }

}
