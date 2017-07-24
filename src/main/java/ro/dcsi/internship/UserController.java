package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class UserController implements UserDaoInterface {

  @Override
  public void writeUsers(String file, User... users) {
    Writer writer;

    try {
      writer = new FileWriter(file);
      StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
      beanToCsv.write(new ArrayList<User>(Arrays.asList(users)));
      writer.close();
    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e1) {
      e1.printStackTrace();
    }
  }

  @Override
  public List<User> readUsers(String file) {
    List<User> beans = null;

    try {
      beans = new CsvToBeanBuilder(new FileReader(file)).withType(User.class).build().parse();
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return beans;
  }

}
