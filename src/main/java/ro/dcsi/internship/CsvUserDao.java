package ro.dcsi.internship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import de.siegmar.fastcsv.writer.CsvAppender;
import de.siegmar.fastcsv.writer.CsvWriter;

/**
 * Created by Catalin on 7/3/2017.
 */
public class CsvUserDao implements UserDao {
  List<User> database;
  CsvReader reader;
  private String filename;
  private final char separator;

  public CsvUserDao(String filename) {
    this.filename = filename;
    separator = ';';
    reader = new CsvReader(filename,separator);
  }

  public CsvUserDao(String filename, char separator) {
    this.filename = filename;
    this.separator = separator;
    reader = new CsvReader(filename,separator);
  }

  @Override
  public boolean userExists(String id) {
    for (User u : database)
      if (u.getAttributeValue("_id") != null && u.getAttributeValue("_id").equals(id))
        return true;
    return false;
  }

  @Override
  public Optional<User> getUser(String id) {
    for (User u : database)
      if (u.getAttributeValue("_id") != null && u.getAttributeValue("_id").equals(id))
        return Optional.of(u);
    return Optional.empty();
  }

  @Override
  public boolean deleteUser(String id) {
    Optional<User> user = getUser(id);
    if (user.isPresent()) {
      database.remove(user.get());
      return true;
    }
    return false;
  }

  @Override
  public boolean updateUser(User user) {
    boolean remove = deleteUser(user.getId());
    if (remove) {
      database.add(user);
      return true;
    }
    return false;
  }

  @Override
  public boolean addUser(User user) {
    database.add(user);
    return true;
  }

  @Override
  public Iterator<User> iterator() {
    return new CsvDBIterator();
  }

  @Override
  public Iterator<User> read() {
    database = reader.readUsers();
    return iterator();
  }

  class CsvDBIterator implements Iterator<User> {
    int currentIndex;

    public CsvDBIterator() {
      currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
      if (currentIndex < database.size())
        return true;
      return false;
    }

    @Override
    public User next() {
      if (hasNext())
        return database.get(currentIndex++);
      return new User("-1", new HashMap<>());
    }
  }

  @Override
  public void write(Iterator<User> iterator) {
    File file = new File(filename);
    CsvWriter csv = new CsvWriter();
    csv.setFieldSeparator(separator);

    try (CsvAppender appender = csv.append(new FileWriter(file))) {
      if (!iterator.hasNext())
        return;
      User u = iterator.next();

      // Append headers
      Set<String> headers = u.getAttributeSet();
      for (String s : headers)
        appender.appendField(s);
      appender.endLine();

      // Write users
      do {
        LinkedList<String> list = new LinkedList<String>();
        for (String s : headers)
          list.add(u.getAttributeValue(s));
        Object[] objectArray = list.toArray();
        String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);
        appender.appendLine(stringArray);

        // Iterate
        if (iterator.hasNext())
          u = iterator.next();
        else
          u = new User("-10", new HashMap<>());

      } while (!u.getId().equals("-10"));

    } catch (IOException e) {
      System.err.print("Write CSV ERROR!");
      throw new RuntimeException("Error writting to file", e);
    }

  }
}
