package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class UserDaoSorin implements UserDao {

  @Override
  public void writeUsers(String file, TheUser... users) {

//    boolean fileExists = new File(file).exists();
    try {
      CSVWriter writer = new CSVWriter(new FileWriter(file, false), ',');
//      if (!fileExists) {
        // here add Header if necessary
//      }
      List<String[]> data = new ArrayList<>();
      for (TheUser u : users) {
        data.add(new String[] { u.getUsername(), u.getPasswd(), u.getFullname(), Integer.toString(u.getPermissions()),
            Integer.toString(u.getAge()), u.getCountry(), u.getEmail() });
      }

      writer.writeAll(data);
      writer.close();
    } catch (IOException e) {
      System.out.println("I/O Exception!");
    }
  }

  @Override
  public List<TheUser> readUsers(String file) {
    List<TheUser> theusers = new ArrayList<TheUser>();
    try {
      CSVReader reader = new CSVReader(new FileReader(file), ',');
      String[] buff = null;
      while ((buff = reader.readNext()) != null) {
        TheUser u = new TheUser.Builder().setUsername(buff[0])
        .setUsername(buff[0])
        .setPasswd(buff[1])
        .setFullname(buff[2])
        .setPermissions(Integer.parseInt(buff[3]))
        .setAge(Integer.parseInt(buff[4]))
        .setCountry(buff[5])
        .setEmail(buff[6]).build();
        theusers.add(u);
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found!");
    } catch (IOException e) {
      System.out.println("I/O Exception!");
    }
    return theusers;
  }

}
