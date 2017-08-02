package ro.dcsi.internship;

import java.util.UUID;

import com.google.common.base.Preconditions;
import com.opencsv.bean.CsvBindByName;

public class TheUser {
  public static String generateUserId() {
    return UUID.randomUUID().toString();
  }

  @CsvBindByName
  public final String username;
  @CsvBindByName
  public final String passwd;
  @CsvBindByName(required = false)
  public final String fullname;
  public final int permissions;
  public final int age;
  @CsvBindByName
  public final String id;
  @CsvBindByName
  public final String email;
  @CsvBindByName
  private final String firstname;
  @CsvBindByName
  private final String lastname;

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

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  //TODO Don't remove it. It's used by opencsv via reflection.
  public TheUser() {
    this("<cannot happen: no name-private-constructor>");
  }

  public TheUser(String name) {
    this(name, "", "", "<no-email>");
  }

  public TheUser(String username, String firstname, String lastname, String email) {
    this(generateUserId(), username, "", firstname, lastname, "", 0, 0, email);
  }

  public TheUser(String id, String username, String passwd, String firstname, String lastname, String fullname,
      int permissions, int age, String email) {
    this.username = username;
    this.passwd = passwd;
    this.fullname = fullname;
    this.firstname = firstname;
    this.lastname = lastname;
    this.permissions = permissions;
    this.age = age;
    this.id = id;
    this.email = email;
    Preconditions.checkNotNull(id);
  }

  public String toString() {
    return "TheUser{" + "username='" + username + '\'' + ", passwd='" + passwd + '\'' + ", fullname='" + fullname + '\''
        + ", permissions=" + permissions + ", age=" + age + ", id='" + id + '\'' + ", email='" + email + '\''
        + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + '}';
  }
}
