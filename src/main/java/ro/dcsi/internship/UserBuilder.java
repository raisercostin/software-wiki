package ro.dcsi.internship;

public class UserBuilder {
  private String username;
  private String passwd;
  private String fullname;
  private int permissions;
  private int age;
  private String country;
  private String email;

  public UserBuilder setUsername(String username) {
     //String a = TheUser.this.fullname;
    this.username = username;
    return this;
  }
  public UserBuilder setPasswd(String passwd) {
    this.passwd = passwd;
    return this;
  }
  public UserBuilder setFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }
  public UserBuilder setPermissions(int permissions) {
    this.permissions = permissions;
    return this;
  }
  public UserBuilder setAge(int age) {
    this.age = age;
    return this;
  }
  public UserBuilder setCountry(String country) {
    this.country = country;
    return this;
  }
  public UserBuilder setEmail(String email) {
    this.email = email;
    return this;
  }
  public TheUser build() {
    return new TheUser(username,passwd,fullname,permissions,age,country,email);
  }
}