package ro.dsci.internship;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VladUserDao implements UserDao {

  public static void main(String[] args) {
    List<User> users = new VladUserDao().readUsers("src/test/resources/CVSTest.csv");
    for (User b : users) {
      // System.out.println(b);
    }
  }

  static final String COMMA_DELIMITER = ",";
  static final String NEW_LINE_SEPARATOR = "\n";
  static final String FILE_HEADER = "id,username,firstname,lastname,email";

  @Override
  public List<User> readUsers(String fileName) {
    List<User> users = new ArrayList<>();
    Path pathToFile = Paths.get(fileName);
    Map<String, Integer> header = getHeader(pathToFile);

    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
      String line = br.readLine();// ignore header
      line = br.readLine();
      while (line != null) {
        String[] attributes = line.split(",");
        User user = createUser(attributes, header);
        users.add(user);
        line = br.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return users;
  }

  private Map<String, Integer> getHeader(Path pathToFile) {

    Map<String, Integer> map = new HashMap<>();

    try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
      String firstLine = br.readLine();
      if (firstLine == null) {
        throw new IOException("The file: " + pathToFile + " is empty!");
      }
      final List<String> columns = Arrays.asList(firstLine.split(","));
      for (int i = 0; i < columns.size(); i++) {
        map.put(columns.get(i), i);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return map;
  }

  private User createUser(String[] row, Map<String, Integer> header) {
    String id = row[header.get("id")];
    String usern = row[header.get("username")];
    String prenume = row[header.get("firstname")];
    String nume = row[header.get("lastname")];
    String email = row[header.get("email")];

    return new User(id, usern, prenume, nume, email);
  }

  @Override
  public void writeUsers(List<User> users, String fileName) {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(fileName);
      fileWriter.append(FILE_HEADER.toString());
      fileWriter.append(NEW_LINE_SEPARATOR);
      for (User user : users) {
        fileWriter.append(user.id);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.username);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.firstname);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.lastname);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.email);
        fileWriter.append(NEW_LINE_SEPARATOR);
      }
      System.out.println("CSV file was created successfully !!!");
    } catch (Exception e) {
      System.out.println("Error in CsvFileWriter !!!");
      e.printStackTrace();
    } finally {
      try {
        fileWriter.flush();
        fileWriter.close();
      } catch (IOException e) {
        System.out.println("Error while flushing/closing fileWriter !!!");
        e.printStackTrace();
      }
    }
  }

  public List<User> sumOfUsers(String file, List<User> users) {

    List<User> u1 = readUsers(file);
    for (User u : u1) {
      users.add(u);
    }

    return users;

  }

}
