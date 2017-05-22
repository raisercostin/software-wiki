package ro.dcsi.internship;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {
//    public static void main( String[] args ) throws IOException {
//    	export("users100.csv");
//    }	
	public static void export(String inputFilename, String outputFileName) throws IOException {
    	FileReader fr = new FileReader(inputFilename);
    	BufferedReader br = new BufferedReader(fr);
    	FileWriter f0 = new FileWriter(outputFileName);
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
