package ro.dsci.internship;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserSync {

  public static List<User> readUsers(String locatie) {
    List<User> lista = new ArrayList<>();
    try (FileInputStream fis = new FileInputStream(locatie);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] userDetails = line.split(",");
        String Nume = userDetails[0];
        String Prenume = userDetails[1];
        String email = userDetails[2];
        User user = new User(Nume, Prenume, email);
        lista.add(user);
        System.out.println(user);
      }

    } catch (Exception e) {
      throw new RuntimeException("Not Implemented Yet!!!");
    }
    return lista;

  }

}
