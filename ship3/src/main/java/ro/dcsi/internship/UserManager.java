package ro.dcsi.internship;

public class UserManager 
{
	
    public static void main( String[] args )
    {
    	UserStorage employees = new UserStorage();
    	employees.generateUsers("out.csv", 100);
    	
    }
    
}
