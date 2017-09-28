package ro.dsci.internship;

/**
 * Hello world!
 *
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class CVS 
{
	static List<User> listaUseri = new ArrayList<>();
    public static void main( String[] args )
    {
        String locatie = "src/test/resources/CVSTest.csv";
        String locatie2 = "src/test/resources/CVSTest2.csv";
        citesteCVS(locatie);
        scrieCVS(locatie2);
    }
    
    public static void citesteCVS(String cale){
    	try(
    			FileInputStream fis =new FileInputStream(cale);
    			InputStreamReader isr = new InputStreamReader(fis);
    			BufferedReader br = new BufferedReader(isr);
    			){
    		String line;
    		while( (line = br.readLine() ) !=null){
    			String [] userDetails =line.split(",");
    			String nume = userDetails[0];
    			String prenume = userDetails[1];
    			String email = userDetails[2];
    			User user = new User(nume,prenume,email);
				listaUseri.add(user);
				System.out.println(user);
    		}
    		
    	}catch(Exception e){
    	  throw new RuntimeException(e);
    	}
    }
    
    public static void scrieCVS(String cale){
    	try(PrintStream out = new PrintStream(cale)){
    		for(int i =0;i<listaUseri.size();i++){
    			User user =listaUseri.get(i);
    			out.print(user.nume + ","
    					+user.prenume+ ","
    					+user.email +"\n"
    					);
    		}
    		
    	} catch (FileNotFoundException e) {
        throw new RuntimeException(e);
		}
  }

}
