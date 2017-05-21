package ro.dcsi.internship;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {
	
    public static void main( String[] args ) throws IOException {
    	FileReader fr = new FileReader("D:/workspace/ship2/users100.csv");
    	BufferedReader br = new BufferedReader(fr);
    	FileWriter f0 = new FileWriter("D:/workspace/ship2/users100out.csv");
    	String s;
    	
    	try{ 
    	while((s = br.readLine()) != null) {
    	f0.append(s+",");
    	}
    	}catch (IOException e) {
            e.printStackTrace();
            }
    	fr.close();
    	f0.close();
    	
    }
}
