package ro.dcsi.internship;

import java.util.Set;

public class User {
	
	private String username;
	private Set<String> permisions;
	
	public User(){
		
	}
	
	public User(String username){
		this.username = username;
	}
	
	@Override
	public String toString(){
		return this.username;
	}
}
