package ro.dsci.internship;

public class User {
  public final String username;
  public final String firstname;
  public final String lastname;
  public final String email;

  public User(String usernname, String firstname, String lastname, String email) {
    this.username = usernname;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
  }

  @Override
  public String toString() {
    return "User [Username=" + username + ", prenume=" + firstname + ", nume=" + lastname + ", email=" + email + "]";
  }


  public String toString2() {
      return  username +","+ firstname +","+ lastname +","+ email;
    }
}
