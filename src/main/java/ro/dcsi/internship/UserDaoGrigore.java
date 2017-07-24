package ro.dcsi.internship;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grigore on 20.07.2017.
 */
public class UserDaoGrigore implements UserDao {
  @Override
  public void writeUsers(String file, TheUser... users) {
    String csvFile = "src/main/resources/" + file + ".csv";
    String[] record = new String[users.length];

    int i = 0;
    for (TheUser user : users) {
      record[i] = user.getmUserName();
      i++;
    }
    //TODO cred ca aici imi scrie gresit la linii multiple
    try {
      CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
      writer.writeNext(record);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<TheUser> readUsers(String file) {
    String csvFile = "src/main/resources/" + file + ".csv";
    String[] row = null;
    List<TheUser> theUserList = new ArrayList<TheUser>();
    try {

      CSVReader csvReader = new CSVReader(new FileReader(csvFile));
      while ((row = csvReader.readNext()) != null) {
        theUserList.add(new TheUser(row[0]));
      }

      csvReader.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return theUserList;
  }

}
