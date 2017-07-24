package ro.dcsi.internship;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class TheUser {

  private String username;
  private String passwd;
  private String fullname;
  private int permissions;
  private int age;
  private String country;
  private String email;

  public TheUser() {

  }

  public TheUser(String name) {
    this(name, "", "", 0, 0, "", "");
  }

  public TheUser(String name, String pass, String fname, int perms, int age, String country, String mail) {
    setUsername(name);
    setPasswd(pass);
    setFullname(fname);
    setPermissions(perms);
    setAge(age);
    setCountry(country);
    setEmail(mail);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public int getPermissions() {
    return permissions;
  }

  public void setPermissions(int permissions) {
    this.permissions = permissions;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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