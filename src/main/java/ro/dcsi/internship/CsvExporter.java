package ro.dcsi.internship;

import java.io.*;
import java.util.*;

public class CsvExporter {
	public CsvExporter() {
		System.out.println("instantiated");
	}
	public void export(String inputFileName, String outputFileName) {
		List<User> users = readUsers(inputFileName);
		export(users, outputFileName);
	}
	public void export(List<User> users, String outputFileName){
		try (FileWriter fo = new FileWriter(outputFileName); BufferedWriter out = new BufferedWriter(fo)) {
			for (User user : users) {
				out.write(user + ",\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readHeading(String fileName) {
		ArrayList<User> users = new ArrayList<User>();
		String s = new String();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			s = br.readLine();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return s;
	}

	public List<User> readUsers(String csvFile) {
		ArrayList<User> users = new ArrayList<>();
        String csvSplitBy = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
        	boolean firstLine = true;
            String line = "";
        	while ((line = br.readLine()) != null){
        		if (!firstLine){
        			String[] splited = line.split(csvSplitBy);
            		//System.out.println("User " + splited[0]+ " " + splited[1] + " has the email adress: " + splited[4]);
            		users.add(new User(splited[0], splited[1], splited[2]));
        		} 
        		firstLine = false;
        	}
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}    
		return users;
	}

	public List<User> readUsersWithoutHeader(String fileName) {
		List<User> users = new ArrayList<>();
		try (FileReader fr = new FileReader(fileName)) {
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				String[] splited = line.split(",");
				users.add(new User(splited[0], splited[1], splited[2]));
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
