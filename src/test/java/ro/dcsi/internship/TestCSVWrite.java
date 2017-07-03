package ro.dcsi.internship;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

/**
 * Created by Catalin on 20/06/2017.
 */
public class TestCSVWrite {

  @Test
  public void HeadersNotDefaultWrite() {
    String filename = "src/test/resources/CSV/headersnotdefault.csv";

    //Read Users
    CsvDB database = new CsvDB(filename);
    Iterator<User> users = database.readUsers();

    //Write Users
    int location = filename.lastIndexOf('.');
    String writeFilename = filename.substring(0, location) + "_backup" + filename.substring(location);
    Exporter exporter = new CsvExporter(writeFilename);
    exporter.export(users);

    //Read Written file
    CsvDB writeDatabase = new CsvDB(filename);
    Iterator<User> usersRead = writeDatabase.readUsers();

    Iterator<User> userReference = database.iterator();

    User uRead;
    User uReference;

    while (usersRead.hasNext() && userReference.hasNext()) {
      uRead = usersRead.next();
      uReference = userReference.next();

      //Compare number if fields
      assertEquals(uRead.getAttributeSet().size(), uReference.getAttributeSet().size());

      //Compare field values
      for (String s : uRead.getAttributeSet())
        assertEquals(uRead.getAttributeValue(s), uReference.getAttributeValue(s));
    }

    //Compare number of entryes
    assertEquals(usersRead.hasNext(), false);
    assertEquals(userReference.hasNext(), false);

  }

  @Test
  public void HeadersLargeWrite() {
    String filename = "src/test/resources/CSV/headerslarge.csv";

    //Read Users
    CsvDB database = new CsvDB(filename);
    Iterator<User> users = database.readUsers();

    //Write Users
    int location = filename.lastIndexOf('.');
    String writeFilename = filename.substring(0, location) + "_backup" + filename.substring(location);
    Exporter exporter = new CsvExporter(writeFilename);
    exporter.export(users);

    //Read Written file
    CsvDB writeDatabase = new CsvDB(filename);
    Iterator<User> usersRead = writeDatabase.readUsers();

    Iterator<User> userReference = database.iterator();

    User uRead;
    User uReference;

    while (usersRead.hasNext() && userReference.hasNext()) {
      uRead = usersRead.next();
      uReference = userReference.next();

      //Compare number if fields
      assertEquals(uRead.getAttributeSet().size(), uReference.getAttributeSet().size());

      //Compare field values
      for (String s : uRead.getAttributeSet())
        assertEquals(uRead.getAttributeValue(s), uReference.getAttributeValue(s));
    }

    //Compare number of entryes
    assertEquals(usersRead.hasNext(), false);
    assertEquals(userReference.hasNext(), false);
  }

}
