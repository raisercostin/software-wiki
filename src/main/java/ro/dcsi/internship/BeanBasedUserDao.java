package ro.dcsi.internship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.raisercostin.jedi.Locations;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class BeanBasedUserDao implements UserDao {

  @Override
  public void writeUsers(String file, TheUser... users) {
    //Locations.current(file).mkdirOnParentIfNecessary();
    try (Writer writer = new FileWriter(file);) {
      StatefulBeanToCsv<TheUser> beanToCsv = new StatefulBeanToCsvBuilder<TheUser>(writer).build();
      beanToCsv.write(Arrays.asList(users));
    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e1) {
      throw new RuntimeException(e1);
    }
  }

  @Override
  public List<TheUser> readUsers(String file) {
    try {
      return new CsvToBeanBuilder(new FileReader(file)).withType(TheUser.class).build().parse();
    } catch (IllegalStateException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
