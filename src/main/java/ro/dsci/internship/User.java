
<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class User implements UserDao {
  public final String nume;
  public final String prenume;
  public final String email;
  
//constructor
  public User(String nume, String prenume, String email) {
	  
    this.nume = nume;
    this.prenume = prenume;
    this.email = email;
    
  }//end constructor
  
  
  
  
  //interfata metoda 1
  @Override
  public List<User> readUsers(String locatie) {
	 List<User> lista=new ArrayList<User>(); 
	 //List =interfata,ptr implementare trb ceva concret ArrayList,Vector,etc. 
	 try{
		 
		 FileReader fisier=new FileReader(locatie);
		
	     BufferedReader br = new BufferedReader(fisier);
	    
	 //ptr prima linie ,cu numele coloanelor   
	     String line=br.readLine();
	     
	     String[] numeColoane=line.split(",");
	     	   
	     
	  //ar trebui sa si dea seama a cata coloana e cu numele,prenumele,email ca sa le pun invers numerele jos la add.
	     int nrNume=0;
	     int nrPrenume=0;
	     int nrEmail=0;
	     
	     for(int i=0;i<numeColoane.length;i++)
	    	 {
	    		 if(numeColoane[i].equals("Nume"))
	    			 {
	    				 nrNume=i;
	    				 System.out.println("numele e pe  a" + nrNume+ "a coloana");
	    			 }
	    		 if(numeColoane[i].equals("Prenume"))
	    			 {
	    				 nrPrenume=i;
	    				 System.out.println("prenumele e pe  a" + nrPrenume+ "a coloana");
	    			 }
	    		 if(numeColoane[i].equals("Email"))
	    			 {
	    				 nrEmail=i;
	    				 System.out.println("email e pe  a" + nrEmail+ "a coloana");
	    			 }
	    	 }
	  
	     
	     
	   //ptr urmatoarele linii, cu atributele  
	    
	     line=br.readLine();
	     while(line!=null)
	    	 {
	     String[] atribute=line.split(",");
	     User usernou=new User(atribute[nrNume],atribute[nrPrenume],atribute[nrEmail]); //nrNume, nrPrenume, nrEmail
	     lista.add(usernou);
	   	 line=br.readLine();
	    	 }
	    
	     br.close();
	    		
	    } //end try
	  
	  
	  catch (Exception e)
	    {
	      throw new RuntimeException(e);
	    }//end catch
	
	for(int i=0;i<lista.size();i++)
		{
			
			System.out.println(lista.get(i));
		}
	
	    return lista;
	    
  }//end interfata metoda 1
  
  

  
  
  
  
//interfata medota 2 
  @Override
  public void writeUsers(List<User> users, String locatie) {


	  try {
		  
	     FileWriter a = new FileWriter(locatie, true);
	     BufferedWriter b = new BufferedWriter(a);
         PrintWriter c = new PrintWriter(b);
	    // c.println();
         User usernou=users.get(0);
         String atribute[]=usernou.toString2().split(",");
       
   
   c.println( atribute[0]+ " , " + atribute[1] + ", " + atribute[2]);
   c.close();
	  
	    }//end try
	  
	 
	  
	    catch (Exception e) 
	    {
	      throw new RuntimeException(e);
	    }

  }//end interfata metoda 2


  public String toString2() {
	    return  nume +","+ prenume +","+ email;
	  }

  @Override
  public String toString() {
    return "User[nume=" + nume + ", prenume=" + prenume + ", email=" + email + "]";
  }





  
  
 
}// end class User
=======
public class User {
	private String nume;
	private String prenume;
	private String email;

	public User(String nume, String prenume, String email) {
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [nume=" + nume + ", prenume=" + prenume + ", email=" + email + "]";
	}


}
>>>>>>> origin/user/vladvasile3
