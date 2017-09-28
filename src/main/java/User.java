
public class User {
	private String name;
	private int age;
	private String username;
	
	public User(String name, int age, String username) {
		this.name = name;
		this.age = age;
		this.username = username;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge() {
		this.age = age;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername() {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "User: nume= " + name +", varsta= " + age + ", username= " + username;
	}

}
