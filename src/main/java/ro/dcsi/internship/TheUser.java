package ro.dcsi.internship;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class TheUser {

  @CsvBindByName
  private final String username;
  private final String passwd;
  private final String fullname;
  private final int permissions;
  private final int age;
  private final String country;
  private final String email;

  public TheUser() {
    this("no name user");
  }

  public TheUser(String name) {
    this(name, "", "", 0, 0, "", "");
  }

  public TheUser(String username, String passwd, String fullname, int permissions, int age, String country,
      String email) {
    this.username = username;
    this.passwd = passwd;
    this.fullname = fullname;
    this.permissions = permissions;
    this.age = age;
    this.country = country;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public String getPasswd() {
    return passwd;
  }

  public String getFullname() {
    return fullname;
  }

  public int getPermissions() {
    return permissions;
  }

  public int getAge() {
    return age;
  }

  public String getCountry() {
    return country;
  }

  public String getEmail() {
    return email;
  }

  public String toString() {
    return "[" + getUsername() + "::" + getFullname() + "::" + getPermissions() + "::" + getPermissions() + "::"
        + getEmail() + "]";
  }

}

@SuppressWarnings("serial")
class TheUser2 implements Serializable {
  // private String id;
  @CsvBindByName
  private String name;
  // private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TheUser2(String name) {
    this.name = name;
  }

  public TheUser2() {
    this.name = "NO_NAME";
  }
}