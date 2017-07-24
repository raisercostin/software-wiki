package ro.dcsi.internship;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class TheUser {

  public static class Builder {
    private String username;
    private String passwd;
    private String fullname;
    private int permissions;
    private int age;
    private String country;
    private String email;

    public Builder setUsername(String username) {
       //String a = TheUser.this.fullname;
      this.username = username;
      return this;
    }
    public Builder setPasswd(String passwd) {
      this.passwd = passwd;
      return this;
    }
    public Builder setFullname(String fullname) {
      this.fullname = fullname;
      return this;
    }
    public Builder setPermissions(int permissions) {
      this.permissions = permissions;
      return this;
    }
    public Builder setAge(int age) {
      this.age = age;
      return this;
    }
    public Builder setCountry(String country) {
      this.country = country;
      return this;
    }
    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }
    public TheUser build() {
      return new TheUser(username,passwd,fullname,permissions,age,country,email);
    }
  }

  @CsvBindByName
  private final String username;
  private final String passwd;
  private final String fullname;
  private final int permissions;
  private final int age;
  private final String country;
  private final String email;

  //needed by com.opencsv.CsvToBean
  @Deprecated
  public TheUser() {
    this("empty user");
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