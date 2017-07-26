package ro.dcsi.internship;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.raisercostin.jedi.Locations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Grigore on 20.07.2017.
 */
public class UserDaoGrigore implements UserDao {
  @Override
  public void writeUsers(String file, TheUser... users) {
    String csvFile = file + ".csv";
    Locations.current(csvFile).mkdirOnParentIfNecessary();

    try {
      CSVWriter writer = new CSVWriter(new FileWriter(csvFile));

      // write header
      String[] header = { "username", "password", "name", "permissions", "age", "country", "email" };
      writer.writeNext(header);
      for (TheUser u : users) {
        writer.writeNext(new String[] { u.username, u.passwd, u.fullname, Integer.toString(u.permissions),
              Integer.toString(u.age), u.country, u.email });
      }
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("resource")
  @Override
  public List<TheUser> readUsers(String file) {
    String csvFile = file + ".csv";
    List<TheUser> theUserList = new ArrayList<TheUser>();
    try {
      String[] row = null;
      try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
        csvReader.readNext();
        while ((row = csvReader.readNext()) != null) {
          try {
            TheUser user = new UserBuilder().setUsername(row[0]).setPasswd(row[1]).setFullname(row[2])
                .setPermissions(Integer.parseInt(row[3])).setAge(Integer.parseInt(row[4])).setCountry(row[5])
                .setEmail(row[6]).build();
            theUserList.add(user);
          } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't parse line [" + Arrays.toString(row) + "] from file " + csvFile, e);
          }
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return theUserList;
  }

  public List<TheUser> exchangeColumns(int firstColumn, int secondColumn, String file) {
    String csvFile = file + ".csv";
    List<TheUser> theUserList = new ArrayList<TheUser>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(csvFile));
      String row = null;
      String strtemp;
      while ((row = br.readLine()) != null) {
        // use comma as separator
        String[] cols = row.split(",");
        strtemp = cols[firstColumn];
        cols[firstColumn] = cols[secondColumn];
        cols[secondColumn] = strtemp;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return theUserList;
  }

  public List<TheUser> generateUsers(int n) {
    List<TheUser> theUserList = new ArrayList<>();
    Faker faker = new Faker();
    for (int i = 0; i < n; i++) {
      Integer permission = (Math.random() < 0.5) ? 0 : 1;
      Integer age = faker.number().numberBetween(0, 100);
      TheUser user = new UserBuilder().setUsername(faker.name().username()).setPasswd(faker.idNumber().valid())
          .setFullname(faker.name().fullName()).setPermissions(permission).setAge(age)
          .setCountry(faker.address().country()).setEmail(faker.name().username() + "@gmail.com").build();
    }
    return theUserList;
  }
}
