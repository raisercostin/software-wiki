package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

public class FileUserManager2 implements UserSync {
  @Override
  public Iterator<User> readUsers(String fileName) {
    List<User> empList = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader(fileName), ',', '"', 1);) {
      /**
       * Reading the CSV File Delimiter is comma Start reading from line 1
       */
      String[] employeeDetails = null;
      while ((employeeDetails = csvReader.readNext()) != null) {
        User emp = new User(employeeDetails[0], employeeDetails[1], employeeDetails[2], employeeDetails[3],
            employeeDetails[4]);

        empList.add(emp);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return empList.iterator();
  }

  public Iterable<User> readUsersFromHugeFile(String fileName) {
    List<User> empList = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader(fileName), ',', '"', 1)) {
      /**
       * Reading the CSV File Delimiter is comma Start reading from line 1
       */
      String[] employeeDetails = null;
      while ((employeeDetails = csvReader.readNext()) != null) {
        User emp = new User(employeeDetails[0], employeeDetails[1], employeeDetails[2], employeeDetails[3],
            employeeDetails[4]);
        empList.add(emp);
      }
    } catch (Exception ee) {
      throw new RuntimeException("While reading users from file " + fileName, ee);
    }
    return empList;
  }

}
