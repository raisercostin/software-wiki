package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class UserDaoSorin implements UserDao{

  @Override
  public void writeUsers(String file, TheUser... users) {
   
    
  }

  @Override
  public List<TheUser> readUsers(String file) {
    try {
    CSVReader reader = new CSVReader(new FileReader(file), ',');
    List<TheUser> theusers = new ArrayList<TheUser>();
    String[] buff = null;
    while ((buff = reader.readNext()) != null) {
      TheUser u = new TheUser();
      //TODO
    }
    reader.close();
    }catch (FileNotFoundException e) {
     System.out.println("File Not Found!");
    } catch (IOException e) {
      System.out.println("I/O Exception!");
    }
    return null;
  }
  

}
