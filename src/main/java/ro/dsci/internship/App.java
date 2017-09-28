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
     
				 User user1=new User("GeorgescuA","Alexandru","IT","Iasi");
				
				
				user1.write("fisier.csv");
				user1.read();
         
			}// end main method
		
	}// end class App
