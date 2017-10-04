package ro.dsci.internship;

public class User {
  public final String username;
  public final String firstname;
  public final String lastname;
  public final String email;

  public User(String usern, String prenume, String nume, String email) {
    this.username = usern;
    this.firstname = prenume;
    this.lastname = nume;
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
