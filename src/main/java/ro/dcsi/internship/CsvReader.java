package ro.dcsi.internship;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvRow;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Catalin on 7/3/2017.
 */
public class CsvReader {
  private String Filename;
  private char separator = ';';

  public CsvReader(String filename) {
    Filename = filename;
  }

  public CsvReader(String filename, char separator) {
    Filename = filename;
    this.separator = separator;
  }

  public List<User> readUsers() {
    //Opening file
    try (FileReader reader = new FileReader(new File(Filename))) {

      //Init reader for FastCSV
      de.siegmar.fastcsv.reader.CsvReader csv = new de.siegmar.fastcsv.reader.CsvReader();

      //Csv Options
      csv.setContainsHeader(true);
      csv.setFieldSeparator(separator);

      //Creating container
      CsvContainer container = csv.read(reader);

      //Reading headers
      List<String> headers = new ArrayList<>(container.getHeader());

      //Convert headers to lowercase
      /*for(String s:headers) {
         headers.set(headers.indexOf(s), s.toLowerCase());
      }*/

      //User list
      List<User> users = new ArrayList<>();
      Map<String, String> mapBuffer;

      //Read Users
      for (CsvRow row : container.getRows()) {
        mapBuffer = new HashMap<>();

        for (int i = 0; i < row.getFieldCount(); i++) {
          mapBuffer.put(headers.get(i), row.getField(i));
        }
        String id = mapBuffer.containsKey("_id") ? mapBuffer.get("_id") : Integer.toString(row.hashCode());
        User user = new User(id, mapBuffer);
        users.add(user);
      }
      reader.close();
      return users;

    } catch (IOException e) {
      System.err.print("Open for read, file error");
      throw new RuntimeException("Error opening file for read", e);
    }
  }
}
