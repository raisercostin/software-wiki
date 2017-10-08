package ro.dsci.internship;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {
  @Test
  public void testAppExecuteMain() {
	  
	  List<User> users=new ArrayList<User>();		
	  User user1=new User("777","p.alex","Alexandru","Popescu", "email1@gmail.com");	    	    
	  users.add(user1);
	  
 //	user1.writeUsers(users,"fisier.csv");
    Assert.assertEquals(users,new IoanaUserDao().readUsers("fisier.csv"));
  }
}
