package ro.dsci.internship;


import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;

public class User implements Serializable {
	@CsvBindByName
	public int id;
	@CsvBindByName
	public String username;
	@CsvBindByName
	public String firstname;
	@CsvBindByName
	public String lastname;
	@CsvBindByName
	public String email;

	
	public User(){
		
	}
	public User(int id, String usernname, String firstname, String lastname, String email) {
		this.id = id;
		this.username = usernname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return this.id + "," + this.username + "," + this.firstname + "," + this.lastname + "," + this.email;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		if (this.id == user.id && this.username == user.username && this.firstname == user.firstname
				&& this.lastname == user.lastname && this.email == user.email) {
			return true;
		}
		return false;

	}
	

}