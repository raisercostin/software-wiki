package ro.dcsi.internship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSync 
{

	public static List<User> readUser(String title) 
	{
		// TODO Auto-generated method stub
		//throw new RuntimeException("Not implemented yet!");
		//return null;
		Scanner sc = null;
		List<User> lu = new ArrayList<User>();
		try 
		{
			 sc = new Scanner(new File(title)).useDelimiter(",[\\n\\r]{0,2}");
			 
			 while(sc.hasNextLine())
		     {
		     	String line = sc.nextLine();
		       	String w[] = line.split("[,]");
		       	User curent_user = new User();
		       	
		        curent_user.name = w[0]+" "+w[1];
		        curent_user.age = Integer.parseInt(w[2]);
		        curent_user.salary = Double.parseDouble(w[3]);
		        for(int i = 4; i < w.length; i++)
		        	if(curent_user.details != null)
		        		curent_user.details = curent_user.details + w[i]+" ";
		        	else
		        		curent_user.details = w[i] + " ";
		        
		        lu.add(curent_user);
		            
		     }
			 
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			sc.close();
			
			for(User user:lu) //auto-test
				System.out.println(user);
			
		}
		return lu;
		
	}

	public static Iterable<User> readUsersFromHugeFile(String title) 
	{
		//throw new RuntimeException("Not implemented yet!!!!");
		Scanner sc = null;
		Iterable<User> lu = new ArrayList<User>();
		
		return lu;
	}

}
