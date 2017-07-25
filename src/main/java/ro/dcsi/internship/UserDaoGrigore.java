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
import java.util.List;

/**
 * Created by Grigore on 20.07.2017.
 */
public class UserDaoGrigore implements UserDao {
  @Override
  public void writeUsers(String file, TheUser... users) {
    String csvFile = file + ".csv";
    Locations.current(csvFile).mkdirOnParentIfNecessary();

    Faker faker = new Faker();

    try {
      CSVWriter writer = new CSVWriter(new FileWriter(csvFile));

      //write header
      String[] header = { "username", "password", "name", "permissions", "age", "country", "email" };
      writer.writeNext(header);

      List<String[]> theUserList = new ArrayList<>();

      //generate random users in csv file
      for (int i = 0; i < 5; i++) {
        Integer permission = (Math.random() < 0.5) ? 0 : 1;
        Integer age = faker.number().numberBetween(0, 100);
        theUserList.add(new String[] { faker.name().username(), faker.idNumber().valid(), faker.name().fullName(),
            permission.toString(), age.toString(), faker.address().country(), faker.name().username() + "@gmail.com" });
      }
      writer.writeAll(theUserList);
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<TheUser> readUsers(String file) {
    String csvFile = file + ".csv";
    List<TheUser> theUserList = new ArrayList<TheUser>();
    try {
      String[] row = null;
      CSVReader csvReader = new CSVReader(new FileReader(csvFile));
      csvReader.readNext();
      while ((row = csvReader.readNext()) != null) {
        TheUser user = new UserBuilder().setUsername(row[0]).setPasswd(row[1]).setFullname(row[2])
            .setPermissions(Integer.parseInt(row[3])).setAge(Integer.parseInt(row[4])).setCountry(row[5])
            .setEmail(row[6]).build();
        theUserList.add(user);
      }
      csvReader.close();
      BufferedReader br = new BufferedReader(new FileReader(csvFile));
      String line = null;
      String strtemp;

      while ((line = br.readLine()) != null) {
        // use comma as separator
        String[] cols = line.split(",");
        strtemp = cols[0];
        cols[0] = cols[1];
        cols[1] = strtemp;
        System.out.println("Coulmn 0= " + cols[0] + "Coulmn 1= " + cols[1]);
      }
      writeUsers(file);
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
}
