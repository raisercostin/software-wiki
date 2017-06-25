package ro.dcsi.internship;

public class User {
	public final String name;
	public final int age;
	public final double salary;
	public final String details;
	
	public User(String name, int age, double salary, String details) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.details = details;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", salary=" + salary + ", details=" + details + "]";
	}
}
