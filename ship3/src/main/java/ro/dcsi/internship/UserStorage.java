package ro.dcsi.internship;

import java.io.File;

public class UserStorage {
	
	private String inputFilePath = "input.csv";
	private String outputFilePath = "output.csv";
	private List<User> = new ArrayList<User>();
	
	public UserStorage(){
		
	}
	
	public UserStorage(String inputFilePath, String outputFilePath){
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}
	
	public void exportUsers(){
		
	}
	
	public void importUsers(){
		File inputFile = new File(inputFilePath);
		
	}
	
	public void setInputFilePath(String inputFilePath){
		this.inputFilePath = inputFilePath;
	}
	
	public void setOutputFilePath(String inputFilePath){
		this.inputFilePath = inputFilePath;
	}
	
	
	
}
