package ro.dcsi.internship;

public class User {
	public final String surname;
	public final String firstname;
	public final String age;
	public final String nationality;
	public final String country;
	public User(String prenume, String nume, String varsta, String nationalitate, String tara) {
		this.surname = prenume;
		this.firstname = nume;
		this.age = varsta;
		this.nationality = nationalitate;
		this.country = tara;
	}
	@Override
	public String toString() {
		return "User [surname=" + surname + ", firstname=" + firstname + ", age=" + age + ", nationality=" + nationality
				+ ", country=" + country + "]";
	}
	
}
