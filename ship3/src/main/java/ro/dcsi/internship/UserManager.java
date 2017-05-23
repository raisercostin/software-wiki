package ro.dcsi.internship;

import java.util.ArrayList;
import java.util.List;

public class UserManager 
{
	
    public static void main( String[] args )
    {
    	List<User> users = new ArrayList<User>();
    	
    	UserStorage employees = new UserStorage();
    	
    	employees.importUsers();
    	employees.exportUsers();
    	//employees.generateUsers("out.csv", 100);
    	
    }
    
}
