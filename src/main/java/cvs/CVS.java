package cvs;

/**
 * Hello world!
 *
 */
import java.io.*;
import java.util.*;


public class CVS 
{
	static List<String> listaUseri = new ArrayList<>();
    public static void main( String[] args )
    {
        String locatie = "C:\\Users\\gabi\\Desktop\\Roweb\\CVSTest.csv";
        String locatie2 = "C:\\Users\\gabi\\Desktop\\Roweb\\CVSTest2.csv";
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
    			String [] users =line.split(",");
    			for(String user:users){
    				listaUseri.add(user);
    				System.out.println(user);
    			}
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    public static void scrieCVS(String cale){
    	try(PrintStream out = new PrintStream(cale)){
    		for(int i =0;i<listaUseri.size();i++){
    			out.print(listaUseri.get(i) + ",");
    		}
    		
    	} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
    }
    
    
}
