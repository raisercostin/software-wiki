package ro.dsci.internship;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;





public class App
	{
		public static void main(String[] args)
			{

				//write("Georgescu","Alexandru","IT","Iasi","C:\\Users\\Ioana Gabriela\\Desktop\\dcsi\\s3\\s3\\fisier.csv");
				read();
         
			}// end main method
		
		
		
		 public static void write(String Nume,String Prenume,String Job,String Locatie,String filePath)
             {
            				try
							{
                               FileWriter a=new FileWriter(filePath,true);
                               BufferedWriter b=new BufferedWriter(a);
                               PrintWriter c=new PrintWriter(b);
                               
                               //scriem intr un fisier
                               //punand la sfarsitul fisierului ce am scris/nu stergand tot si scriind peste
                               //punem ce vrem sa scriem intr-un buffer si
                               //facem un obiect cu care sa putem println-ui ce trebuie
                               c.println(Nume+" , "+ Prenume + ", " +Job+","+Locatie);
                               c.close();
                               
							}

						catch (Exception e)
							{
								e.printStackTrace();
							}

						
					
             }//end write 

			
		 // parte de citire din csv

		public static void read() {
			String numeFisier = "fisier.csv";
			File obiectFisier = new File(numeFisier);
			// cream un obiect de tip file pe care l pasam in constructorul lui scanner

			try
				{
					Scanner ccz = new Scanner(obiectFisier);
					// cream un obiect de tip scanner ptr a lua inputul/fisierul csv
					while (ccz.hasNext()) // cat timp inca mai citeste ceva din fisier
						{
							// afisam ce a citit
							String citit = ccz.nextLine();
							System.out.println(citit);
						}

				} catch (Exception e)

				{
					e.printStackTrace();
				}
		}//end read
	}// end class App
