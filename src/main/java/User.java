
public class User {
	public final String name;
	public final int age;
	public final String username;
	
	public User(String name, int age, String username) {
		this.name = name;
		this.age = age;
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "User: nume= " + name +", varsta= " + age + ", username= " + username;
	}
}
