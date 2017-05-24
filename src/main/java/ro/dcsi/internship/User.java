package ro.dcsi.internship;

import java.util.Set;

public class User {
	public final String username;
	public final String email = "todo";
	private Set<String> permisions;
	
	public User(String username){
		this.username = username;
	}
	
	@Override
	public String toString(){
		return this.username;
	}
}
