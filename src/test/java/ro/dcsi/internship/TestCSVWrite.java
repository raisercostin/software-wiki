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
    String filename2 = "headersnotdefault.csv";
    String path = "src/test/resources/CSV/"+filename2;

    //Read Users
    CsvDB database = new CsvDB(path);
    Iterator<User> users = database.readUsers();

    //Write Users
    int location = filename2.lastIndexOf('.');
    String path2 = "target/"+filename2.substring(0, location) + "_backup" + filename2.substring(location);
    Exporter exporter = new CsvExporter(path2);
    exporter.export(users);

    //Read Written file
    CsvDB writeDatabase = new CsvDB(path2);
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
    String filename2 = "headerslarge.csv";
    String path = "src/test/resources/CSV/"+filename2;

    //Read Users
    CsvDB database = new CsvDB(path);
    Iterator<User> users = database.readUsers();

    //Write Users
    int location = filename2.lastIndexOf('.');
    String writeFilename = "target/"+filename2.substring(0, location) + "_backup" + filename2.substring(location);
    Exporter exporter = new CsvExporter(writeFilename);
    exporter.export(users);

    //Read Written file
    CsvDB writeDatabase = new CsvDB(writeFilename);
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
