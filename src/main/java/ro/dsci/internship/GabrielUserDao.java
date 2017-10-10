package ro.dsci.internship;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class GabrielUserDao implements UserDao {

  @Override
  public List<User> readUsers(String locatie) {
    ArrayList<User> rezultat;
    try {
      rezultat = (ArrayList<User>) new CsvToBeanBuilder(new FileReader(locatie)).withType(User.class).build().parse();
    } catch (Exception e) {
      throw new RuntimeException("laCitire", e);
    }
    return rezultat;
  }

  @Override
  public void writeUsers(List<User> users, String locatie) {
    try {
      stergeDacaExista(locatie);
      Writer writer = new FileWriter(locatie);
      StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
      beanToCsv.write(users);
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException("la Scriere", e);
    }

  }

  public void stergeDacaExista(String locatie) {
    Path p1 = Paths.get(locatie);
    try {
      Files.deleteIfExists(p1);
    } catch (IOException e) {
      throw new RuntimeException("la curatare", e);
    }
  }

}
