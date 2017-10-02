
public class User {
	private String nume;
	private String prenume;
	private String email;

	public User(String nume, String prenume, String email) {
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [nume=" + nume + ", prenume=" + prenume + ", email=" + email + "]";
	}


}
