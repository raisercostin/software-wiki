package ro.dcsi.internship;

import java.util.Set;

public class User {
	public final String username;
	public final String email;
	public final String firstname;
	public final String lastname;

	private Set<String> permisions;
	public User(String username, String email) {
		this(username,email,"NoFirstName","NoLastName");
	}
	public User(String username, String email, String firstname, String lastname) {
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		if(username.contains("\n"))
			throw new RuntimeException("A username shouldn't contain End Of Lines. The username was ["+username+"]");
	}
	@Override
	public String toString() {
		return "User("+username+"<"+email+">)";
	}
	/**A cleaned up username that can be used by forgerock.*/
	//TODO remove a violation of SRP
	public String idFromUsenameForForgeRock() {
		String reg = "[\\\\ \",]";
		return username.replaceAll(reg, "-");
	}
}
