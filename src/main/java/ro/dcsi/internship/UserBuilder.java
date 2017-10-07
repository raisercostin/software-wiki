package ro.dcsi.internship;

public class UserBuilder {
  private String username;
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

  public UserBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public User build() {
    return new User(username, email, firstname, lastname);
  }
}
