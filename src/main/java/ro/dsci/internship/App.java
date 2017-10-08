package ro.dsci.internship;

import java.util.ArrayList;
import java.util.List;

public class App
	{
		public static void main(String[] args)
			{
				List<User> users=new ArrayList<User>();
				
			    User user1=new User(999,"email1","Popescu","Alexandru","email1@gmail.com");
			  
			    
			    
			    users.add(user1);
				
				
			//	user1.writeUsers(users,"fisier.csv");
			    new IoanaUserDao().readUsers("fisier.csv");
         
			}// end main method
		
	}// end class App
