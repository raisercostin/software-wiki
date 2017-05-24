package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserManager 
{
    public static void main( String[] args )
    {
        String csvFile = "/home/madalin/Workspace/Eclipse/WORK/UserSyncApp/ship1/src/main/resources/sample.csv";
        String line = "";
        String csvSplitBy = ",";
        
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
        	boolean firstLine = true;
        	while ((line = br.readLine()) != null){
        		if (!firstLine){
        			String[] users = line.split(csvSplitBy);
            		System.out.println("User " + users[0]+ " " + users[1] + " has the email adress: " + users[4]);
        		} 
        		firstLine = false;
        	}
        } catch (IOException e){
        	e.printStackTrace();
        }
    
    }
}
