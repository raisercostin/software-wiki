package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class ConfigTest {
  static String resourcesFolder = "src/test/resources";
}

class ReadingUsers {
  public String read() throws FileNotFoundException {
    System.out.println("Reading users in order from a CSV file");
    String last = "";
    Scanner sc = new Scanner(new File(ConfigTest.resourcesFolder + "/users.csv"));
    sc.useDelimiter(",");
    System.out.println("User list: ");
    while (sc.hasNext()) {
      last = sc.next();
      System.out.println(last);
    }
    sc.close();
    return last;
  }
}

class WritingUsers {
  public void write() throws FileNotFoundException {
    PrintWriter pw = new PrintWriter(new File(ConfigTest.resourcesFolder + "/users2.csv"));
    StringBuilder sb = new StringBuilder();
    Scanner sc = new Scanner(System.in);
    while (true) {
      String x = sc.next();
      if (x.equals("end")) {
        break;
      } else {
        sb.append(x);
        sb.append(',');
      }
    }
    sc.close();
    pw.write(sb.toString());
    pw.close();
  }

  public String verification() {
    //users2 file doesn't exist in first place
    //if it is created, it will be also written hopefully
    if (new File(ConfigTest.resourcesFolder + "/users2.csv") != null) {
      return "Writing done!";
    }
    return "no";
  }
}

//this app deals only with users
//TODO: add pass and other info besides users
public class App2 {
  public static void main(String[] args) throws FileNotFoundException {
    new ReadingUsers().read();
    new WritingUsers().write();
  }
}