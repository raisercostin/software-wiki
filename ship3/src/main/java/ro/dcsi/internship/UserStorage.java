package ro.dcsi.internship;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserStorage {
	
	private String inputFilePath = "input.csv";
	private String outputFilePath = "output.csv";
	private File file = null;
	private FileWriter fw = null;
	private FileWriter fr = null;
	private BufferedWriter out = null;
	private BufferedWriter in = null;

	private List<User> users = new ArrayList<User>();
	
	public UserStorage(){
		
	}
	
	public UserStorage(String inputFilePath, String outputFilePath){
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}
	
	public void exportUsers(){
				
		try{
			file = new File(outputFilePath);
			fw = new FileWriter(file);
			out = new BufferedWriter(fw);
			
			Iterator<User> iterator = users.iterator();
			
			User tmp;
			
			while(iterator.hasNext()){
				tmp = (User)iterator.next();
				out.write(tmp + ",\n");
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fw != null){
					fw.close();
				}
				if(out != null){
					out.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
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
	
	public List<User> getUsers(){
		return users;
	}
	
	// helper method
	public void generateUsers(String filePath, int nrOfUsersToGenerate){
		try{
			File file = new File(outputFilePath);
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			
			for(int i=1;i<=nrOfUsersToGenerate;i++){
				out.write("User" + i + ",\n");
				out.flush();
			}
			
			fw.close();
			out.close();
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
