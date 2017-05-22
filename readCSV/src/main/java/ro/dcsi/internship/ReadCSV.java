package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadCSV {

	public static void main(String[] args) {
		BufferedReader myCSVreader = null;
		StringBuffer users = new StringBuffer();
		String line = new String();
		try
		{
			myCSVreader = new BufferedReader(new FileReader("HundredUsers.csv"));
			while ((line = myCSVreader.readLine()) != null) {
				users.append(line);
			}
			System.out.println(users);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(myCSVreader != null)
				try{
					myCSVreader.close();
				}
				catch(IOException e1){
					e1.printStackTrace();
				}
		}
		BufferedWriter myCSVwriter = null;
		try
		{
			myCSVwriter = new BufferedWriter(new FileWriter("ExportUsers.csv"));
			myCSVwriter.write(users.toString());
		}
		catch(IOException e2)
		{
			e2.printStackTrace();
		}
		finally
		{
			if(myCSVwriter != null)
				try{
					myCSVwriter.close();
				}
				catch(IOException e3){
					e3.printStackTrace();
				}
		}
	}

}
