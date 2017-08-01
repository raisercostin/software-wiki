package ro.dcsi.internship;

public class UserBuilder {
  private String username;
  private String passwd;
  private String fullname;
  private int permissions;
  private int age;
  private String id;
  private String email;
  private String firstname;
  private String lastname;

  public UserBuilder setUsername(String username) {
    this.username = username;
    return this;
  }

  public UserBuilder setFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserBuilder setLastName(String firstname) {
    this.lastname = firstname;
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

  public UserBuilder setId(String country) {
    this.id = country;
    return this;
  }

  public UserBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public TheUser build() {
    if (id == null) {
      id = TheUser.generateUserId();
    }
    return new TheUser(id, username, passwd, firstname, lastname, fullname, permissions, age, email);
  }
}
