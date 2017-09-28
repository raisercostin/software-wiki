package ro.dsci.internship;
import java.util.*;

public class writefile {
	
	private Formatter x;
	
	public void openFile(){		
	try {
		x = new Formatter("users2.csv");
	}	
	catch(Exception e) {
	throw new RuntimeException(e);
	}
	}
	
	public void addUsers() {
		x.format("%s, %s, %s", "Stefan Mardare", "34", "marrstef");
	}
	
	public void closeFile() {
		x.close();
	}

}
