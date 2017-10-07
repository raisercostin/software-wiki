package ro.dcsi.internship;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class BeanBasedUserDao{
  public void writeUsers(String file, User... users) {
    writeUsers(file, Arrays.asList(users));
  }

  public void writeUsers(String file, List<User> users) {
    try (Writer writer = new FileWriter(file);) {
      @SuppressWarnings("unchecked")
      StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(writer).build();
      beanToCsv.write(users);
    } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e1) {
      throw new WrappedCheckedException(e1);
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<User> load(String file) {
    try (FileReader reader = new FileReader(file)) {
      return new CsvToBeanBuilder(reader).withType(User.class).build().parse();
    } catch (IllegalStateException | IOException e) {
      throw new WrappedCheckedException(e);
    }
  }
}
