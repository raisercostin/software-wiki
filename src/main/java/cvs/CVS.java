package cvs;

/**
 * Hello world!
 *
 */
import java.io.*;
import java.util.*;


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
    			String Nume = userDetails[0];
    			String Prenume = userDetails[1];
    			String email = userDetails[2];
    			User user = new User(Nume,Prenume,email);
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
    			out.print(user.getNume() + ","
    					+user.getPrenume()+ ","
    					+user.getEmail() +"\n"
    					);
    		}
    		
    	} catch (FileNotFoundException e) {
        throw new RuntimeException(e);
		}
  }

}
