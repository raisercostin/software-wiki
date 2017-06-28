package ro.dcsi.internship;

public class User {
  public final String username;
  public final String email;
  public final String firstName;
  public final String lastName;

  public User(String username, String email, String firstName, String lastName) {
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "User [username=" + username + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
        + "]";
  }

}
