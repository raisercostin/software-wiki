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

public class UserController implements UserDao {

  @Override
  public void writeUsers(String file, TheUser... users) {
    Writer writer;

    try {
      writer = new FileWriter(file);
      StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
      beanToCsv.write(new ArrayList<TheUser>(Arrays.asList(users)));
      writer.close();
    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e1) {
      e1.printStackTrace();
    }
  }

  @Override
  public List<TheUser> readUsers(String file) {
    List<TheUser> beans = null;

    try {
      beans = new CsvToBeanBuilder(new FileReader(file)).withType(TheUser.class).build().parse();
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return beans;
  }

}
