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
  public static int howMany = 0;
  
  public boolean isDuplicate(TheUser user, List<TheUser> old) {
    for (TheUser u : old) {
      if (u.username.equals(user.username) || u.email.equals(user.email)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public void writeUsers(String file, TheUser... users) {

    boolean fileExists = new File(file).exists();
    try {
      CSVWriter writer = new CSVWriter(new FileWriter(file, true), ',');
      List<String[]> data = new ArrayList<>();
      List<TheUser> existingUsers = new ArrayList<>();
      
      if (!fileExists) {
        String[] header = new String[] {"Username", "Password", "Full Name", "Permissions", "Age", "Country", "Email"};
        data.add(header);
      } else {
        existingUsers = readUsers(file);
      }
      
      
      for (TheUser u : users) {
        if (!isDuplicate(u, existingUsers)) {
          data.add(new String[] { u.username, u.passwd, u.fullname, Integer.toString(u.permissions),
              Integer.toString(u.age), u.country, u.email });
          howMany++;
        }
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
      reader.readNext(); //skip header
      while ((buff = reader.readNext()) != null) {
        TheUser u = new UserBuilder().setUsername(buff[0])
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
