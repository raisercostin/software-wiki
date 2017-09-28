package cvs;

import java.nio.file.*;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;


public class testSimplu extends TestCase {
    String locatie = "src/test/resources/CVSTest.csv";
    String locatie2 = "src/test/resources/CVSTest2.csv";

	
	public void testeaza(){
		cvs.CVS.citesteCVS(locatie);
	    cvs.CVS.scrieCVS(locatie2);
		Path p1 = Paths.get(locatie2);
		boolean exists = Files.exists(p1);
		if(exists){
			assert true;
		}
	}

	public void testInterface(){
	  List<User> users = UserSync.readUsers(locatie);
	  Assert.assertEquals(4, users.size());
	  Assert.assertEquals("firstuser@gmail.com",users.get(0).getEmail());
	}
}
