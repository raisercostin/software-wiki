package ro.dcsi.internship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
public class UserManager {
    public static void main2( String[] args )
    {
        List<User> users = new CsvExporter().readUsers("/home/madalin/Workspace/Eclipse/WORK/UserSyncApp/ship1/src/main/resources/sample1.csv");
        for (User user : users) {
        	System.out.println("User " + user/*+ " " + users[1] + " has the email adress: " + users[4]*/);
		}
    }

    public static void mainStefan( String[] args )
    {
    	List<User> users = new ArrayList<User>();
    	
    	UserStorage employees = new UserStorage("input.csv","output.csv");
    	
    	employees.importUsers();
    	employees.exportUsers();
    	//employees.generateUsers("out.csv", 100);
    	
    }
}
