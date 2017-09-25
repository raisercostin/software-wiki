package cvs;

import java.nio.file.*;


import junit.framework.TestCase;


public class testSimplu extends TestCase {
    String locatie = "C:\\Users\\gabi\\Desktop\\Roweb\\CVSTest.csv";
    String locatie2 = "C:\\Users\\gabi\\Desktop\\Roweb\\CVSTest2.csv";

	
	public void testeaza(){
		cvs.CVS.citesteCVS(locatie);
	    cvs.CVS.scrieCVS(locatie2);
		Path p1 = Paths.get(locatie2);
		boolean exists = Files.exists(p1);
		if(exists){
			assert true;
		}
	}

}
