package cvs;

public class User {
  public final String nume;
  public final String prenume;
  public final String email;

  public User(String nume, String prenume, String email) {
    this.nume = nume;
    this.prenume = prenume;
    this.email = email;
  }

  @Override
  public String toString() {
    return this.nume + " " + this.prenume + " " + this.email;
  }
}
