package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class UserManager 
{
	private static int getHeaderLocation(String[] headers, String columnName) {
		return Arrays.asList(headers).indexOf(columnName);
	}
	
    public static void main( String[] args )
    {
        String csvFile = "src/main/resources/sample.csv";
        String line = "";
        String csvSplitBy = ",";
                
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
        	boolean isFirstLine = true;
        	int usernameIndex = -1;
        	int emailIndex = -1;
        	
        	while ((line = br.readLine()) != null){
        		if (isFirstLine){
        			// check for index of headers: "username" and "email adress"
        			usernameIndex = getHeaderLocation(line.split(csvSplitBy), "USERNAME");
        			emailIndex = getHeaderLocation(line.split(csvSplitBy), "EMAIL ADDRESS");
        		} else {
        			String[] users = line.split(csvSplitBy);
            		System.out.println("User " + users[usernameIndex]+ " has the email adress: " + users[emailIndex]);
        		}
        		isFirstLine = false;
        	}
        } catch (IOException e){
        	e.printStackTrace();
        }
    
    }
}
